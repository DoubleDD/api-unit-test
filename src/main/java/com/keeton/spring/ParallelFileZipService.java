package com.keeton.spring;

import com.google.common.base.Stopwatch;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ParallelFileZipService {

    public static class Pair<L, R> {

        private Pair() {
        }

        private Pair(L l, R r) {
            this.l = l;
            this.r = r;
        }

        private L l;
        private R r;

        public static <L, R> Pair<L, R> of(L l, R r) {
            return new Pair<>(l, r);
        }

        public L getKey() {
            return l;
        }

        public R getValue() {
            return r;
        }
    }

    public static class ProgressThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber   = new AtomicInteger(1);
        private final        ThreadGroup   group;
        private final        AtomicInteger threadNumber = new AtomicInteger(1);
        private final        String        namePrefix;

        ProgressThreadFactory(String tag) {
            SecurityManager s = System.getSecurityManager();
            group      = (s != null) ? s.getThreadGroup() :
                         Thread.currentThread().getThreadGroup();
            namePrefix = "zipDownload-[ " + tag + " ] " +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    @Data
    public static class ProgressInfo {
        /**
         * 当前进度
         */
        int progress;

        /**
         * 剩余时间(s)
         */
        long timeLeft;

        public String getTime() {
            return getTimeStr(timeLeft);
        }

        public static ProgressInfo of(int progress, long timeLeft) {
            ProgressInfo progressInfo = new ProgressInfo();
            progressInfo.setProgress(progress);
            progressInfo.setTimeLeft(timeLeft);
            return progressInfo;
        }
    }

    public static final int HUNDRED = 100;

    /**
     * 小时对应的秒数
     */
    public static final int HOUR_TO_SECONDS = 3600;

    /**
     * 分钟对应的秒数
     */
    public static final int MINUTE_TO_SECONDS = 60;

    /**
     * 天对应的小时数
     */
    public static final int DAY_TO_HOURS = 24;

    @Value("${minionew.viewUrl:}")
    private String viewUrl;

    @Value("${minionew.endpoint:}")
    private String endpoint;

    @Value("${minionew.port:}")
    private String port;

    @Value("${minionew.bucketName:default}")
    private String bucketName;

    @Value("${minionew.uploadSpeed}")
    private long uploadSpeed;


    private final Executor threadPoolTaskExecutor;

    public ParallelFileZipService(Executor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    /**
     * 异步打包
     *
     * @param zipName              压缩包的文件名
     * @param fileTempDataSupplier 要下载的文件列表
     * @param zipDoneConsumer      文件上传回调
     * @param progressConsumer     进度回调
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void zipDownload(
            String zipName,
            Supplier<List<FileTempData>> fileTempDataSupplier,
            Consumer<ProgressInfo> progressConsumer,
            Consumer<File> zipDoneConsumer
    ) throws InterruptedException, IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 获取要下载的文件列表
        List<FileTempData> fileTempDataList = fileTempDataSupplier.get();
        String             md5Hex           = getMd5Hex(fileTempDataList);
        // 计算压缩后的文件总大小(zip压缩率太高,约为99.9%,故以原文件总大小作为压缩包大小)
        long zipSize = fileTempDataList.stream().filter(FileTempData::isFile).mapToLong(FileTempData::getFileSize).sum();
        // 计算压缩文件所需上传时间(单位为:微秒)
        long zipUploadTime = BigDecimal.valueOf(zipSize).multiply(BigDecimal.valueOf(1000000)).divide(BigDecimal.valueOf(uploadSpeed), 0, RoundingMode.CEILING).longValue();
        // 创建压缩文件
        File file = new File(System.getProperty("user.home") + File.separator + zipName);
        this.createFile(file);
//        redisTemplate.opsForHash().put(md5Hex, zipName, 0 + "," + zipUploadTime);
        // 下载压缩
        FileOutputStream outputStream = new FileOutputStream(file);
        this.zipOut(outputStream, md5Hex, fileTempDataList);
        outputStream.close();
        if (progressConsumer != null) {
            // 上传zip到minio
            final long[]             hasUploadSize            = {0};
            final long[]             hasUploadTime            = {0};
            ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ProgressThreadFactory(zipName));
            // 每秒执行一次
            scheduledExecutorService.schedule(() -> {
                hasUploadSize[0] += uploadSpeed;
                hasUploadTime[0] += 1000 * 1000;
                // 上传过程中实时更新进度
                // 生产环境文件上传速度为9m/s,即9437184byte/s,(剩余时间单位为:微秒)
                int progress = BigDecimal.valueOf(hasUploadSize[0]).multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(zipSize), 0, RoundingMode.FLOOR).intValue();
                long timeLeft = progress > 100 ? 1000000 : zipUploadTime - hasUploadTime[0];
                progress = progress > 100 ? 99 : progress;
                progressConsumer.accept(ProgressInfo.of(progress, timeLeft));
            }, 1, TimeUnit.SECONDS);

            // 文件打包完成
            zipDoneConsumer.accept(file);
            // 文件上传成功,执行器停止
            scheduledExecutorService.shutdown();
            // 进度完成
            progressConsumer.accept(ProgressInfo.of(100, 0));
        } else {
            // 文件打包完成
            zipDoneConsumer.accept(file);
        }


        // 删除本地zip
        file.delete();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        log.debug("下载整体耗时: {}ms", elapsed);
    }


    private void downloadFileWithMultiThread(ZipOutputStream zipOutputStream, String md5Hex, List<FileTempData> fileTempDataList) throws InterruptedException, IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(zipOutputStream)) {
            final CountDownLatch countDownLatch = new CountDownLatch(fileTempDataList.size());
            for (FileTempData fileTempData : fileTempDataList) {
                threadPoolTaskExecutor.execute(() -> this.handler(zipOutputStream, bos, countDownLatch, md5Hex, fileTempData));
            }
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            zipOutputStream.close();
        }
    }

    private void handler(ZipOutputStream zipOutputStream, BufferedOutputStream bos, CountDownLatch countDownLatch, String md5Hex,
                         FileTempData fileTempData) {
        try {
            synchronized (zipOutputStream) {
                String zipPath  = fileTempData.getZipPath();
                Long   fileSize = fileTempData.getFileSize();
                zipOutputStream.putNextEntry(new ZipEntry(zipPath));
                if (!fileTempData.isFile() || fileSize == null || fileSize == 0) {
                    // 往缓存写进度
//                    redisTemplate.opsForHash().put(md5Hex, zipPath.replace("\\", "/"), "100,0");
                    return;
                }
                // 写入文件内容
                Stopwatch stopwatch = Stopwatch.createStarted();
                // 获取输入流
                String        url        = this.replaceUrl(fileTempData.getUrl());
                URLConnection connection = new URL(url).openConnection();
                InputStream   inStream   = connection.getInputStream();
                byte[]        buffer     = new byte[8192];
                // 每次读取字节
                int byteRead;
                // 总读取字节
                int byteSum = 0;
                // 上一次计算速度的时间
                long lastTime = System.currentTimeMillis();
                // 上一次的长度,用来计算速度
                long lastLen = 0;
                // 速度
                long speed;
                // 剩余时间ms
                long lestTime = 0;
                long len      = 0;
                while ((byteRead = inStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, byteRead);
                    len += byteRead;
                    // 获取当前时间
                    long nowTime = System.currentTimeMillis();
                    if (lastLen == 0) {
                        lastLen = len;
                    }
                    // 每隔一段时间以上才进行速度计算
                    if (nowTime - lastTime >= 1000) {
                        //  byte/ms,扩大了1000倍方便计算
                        speed = (BigDecimal.valueOf(len).subtract(BigDecimal.valueOf(lastLen))).multiply(BigDecimal.valueOf(1000))
                                .divide(BigDecimal.valueOf(nowTime).subtract(BigDecimal.valueOf(lastTime)), 0, RoundingMode.FLOOR).longValue();
                        //  ms,扩大了1000倍方便计算
                        lestTime = (BigDecimal.valueOf(fileSize).subtract(BigDecimal.valueOf(len))).multiply(BigDecimal.valueOf(1000))
                                .multiply(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(speed), 0, RoundingMode.CEILING).longValue();
                        lastTime = nowTime;
                        lastLen  = len;
                    }
                    // 累加读取字节
                    byteSum += byteRead;
                    // 往缓存写进度
                    int progress = BigDecimal.valueOf(byteSum).multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(fileSize), 0, RoundingMode.FLOOR).intValue();
                    // 当进度为100时,剩余时间太小,不足1秒,也要置为0
//                    redisTemplate.opsForHash().put(md5Hex, zipPath.replace("\\", "/"), progress + "," + (progress == 100 ? 0 : lestTime));
                }
                bos.flush();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                log.info("文件{},大小为:{}byte,压缩耗时:{}ms,压缩平均速率为:{}byte/ms", zipPath, fileSize, elapsed, fileSize / elapsed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    private String replaceUrl(String fileUrl) throws MalformedURLException {
        URL    url  = new URL(fileUrl);
        String path = url.getPath();
        if (path.startsWith("/minio") || path.startsWith("/oss")) {
            // 这是nginx代理地址
            String minioPath = path.replaceAll("(/minio)|(/oss2)|(/oss)", "");
            return new URL(endpoint + ":" + port + minioPath).toString();
        }
        return fileUrl;
    }

    void zipOut(OutputStream outputStream, String md5Hex, List<FileTempData> fileTempDataList) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            // 设置压缩方法
            zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
            // 多线程下载和压缩
            this.downloadFileWithMultiThread(zipOutputStream, md5Hex, fileTempDataList);
        } catch (IOException e) {
            log.error("打包下载文件失败", e);
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        log.info("文件下载压缩总耗时：{}ms", elapsed);
    }

    /**
     * 创建压缩文件
     *
     * @param file
     */
    private void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据子任务和附件生成md5
     *
     * @return
     */
    private String getMd5Hex(List<FileTempData> files) {
        List<String> fileInfos = new ArrayList<>();
        files.forEach(xx -> {
            String name       = xx.getName();
            Long   length     = xx.getFileSize();
            Date   createTime = xx.getCreateTime();
            String time       = DateFormatUtils.format(createTime, "yyyyMMddHHmmss");
            fileInfos.add(name + "|" + time + "|" + length);
        });
        // md5加密必须含当前日期时间,因为压缩文件名含日期
        return DigestUtils.md5Hex(DateTimeFormatter.ofPattern("yyyy年MM月dd日")
                .format(LocalDate.now())
                .concat(",")
                .concat(String.join(",", fileInfos))
                .getBytes(StandardCharsets.UTF_8)
        );
    }


    /**
     * 转换s为1d2h43m5s的形式
     *
     * @param second
     * @return
     */
    public static String getTimeStr(Long second) {
        if (second == null) {
            return "--";
        }
        if (second < MINUTE_TO_SECONDS) {
            return second + "s";
        }
        long min = second / MINUTE_TO_SECONDS;
        if (min < MINUTE_TO_SECONDS) {
            second = second % MINUTE_TO_SECONDS;
            return min + "m" + (second == 0 ? StringUtils.EMPTY : second + "s");
        }
        long hour = min / MINUTE_TO_SECONDS;
        if (hour < DAY_TO_HOURS) {
            second = second - hour * HOUR_TO_SECONDS;
            min    = second / MINUTE_TO_SECONDS;
            second = second % MINUTE_TO_SECONDS;
            return hour + "h" + (min == 0 ? StringUtils.EMPTY : min + "m") + (second == 0 ? StringUtils.EMPTY : second + "s");
        }
        long day = hour / DAY_TO_HOURS;
        hour   = hour % DAY_TO_HOURS;
        second = second - day * DAY_TO_HOURS * HOUR_TO_SECONDS - hour * HOUR_TO_SECONDS;
        min    = second / MINUTE_TO_SECONDS;
        second = second % MINUTE_TO_SECONDS;
        return day + "d" + (hour == 0 ? StringUtils.EMPTY : hour + "h") + (min == 0 ? StringUtils.EMPTY : min + "m")
                + (second == 0 ? StringUtils.EMPTY : second + "s");
    }

}

