package com.keeton.spring;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadDownload implements Closeable {
    private       boolean showProgress = false;
    // 线程数量
    private final int     threadNum    = 5;
    // 文件url
    private final String  url;
    // 文件大小
    private       int     fileSize;
    // 已下载大小
    AtomicLong downloadSize = new AtomicLong(0);
    // 字节数组
    private byte[] bytes;
    // 每个线程下载的大小
    private int    blockSize;

    public MultiThreadDownload(String url) {
        this.url = url;

    }

    public void download() throws IOException {
        setBlockSize(url);
        if (fileSize < 1) {
            System.out.println("文件大小：0");
            return;
        }
        this.bytes = new byte[fileSize];
        CountDownLatch  latch = new CountDownLatch(showProgress ? threadNum + 1 : threadNum);
        ExecutorService es    = Executors.newFixedThreadPool(showProgress ? threadNum + 1 : threadNum);
        for (int i = 0; i < threadNum; i++) {
            final int threadId = i;
            Runnable runnable = () -> {
                // 计算每个线程下载的开始位置
                int start = threadId * blockSize;
                // 计算每个线程下载的结束位置
                int end = (threadId + 1) * blockSize - 1;
                // 实际下载的结束位置为文件大小-1
                end = Math.min(end, fileSize - 1);
                // 发起http请求,获取字节流
                HttpURLConnection conn  = null;
                InputStream       is    = null;
                int               count = 0;
                try {
                    conn = connect(start, end);
                    is   = new BufferedInputStream(conn.getInputStream());
                    byte[] buffer = new byte[1024];
                    int    len    = -1;
                    while ((len = is.read(buffer)) != -1) {
                        // 拷贝字节流
                        System.arraycopy(buffer, 0, bytes, start, len);
                        // 更新已下载大小
                        start += len;
                        downloadSize.addAndGet(len);
                        count += len;
                    }
                    System.out.println("多线程下载：" + Thread.currentThread().getName() + "----[start: " + start + "] [end: " + end + "] [downloadSize:" + count + "]");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException ignored) {
                        }
                    }
                }
                latch.countDown();
            };
            es.submit(runnable);
        }

        if (showProgress) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    long ds       = downloadSize.get();
                    long progress = ds * 100 / fileSize;
                    System.out.println("下载进度：" + progress + "%\t\t总大小：" + fileSize + "\t\t已下载大小：" + ds);
                    if (ds >= fileSize - 1) {
                        latch.countDown();
                        timer.cancel();
                    }
                }
            }, 0, 1000);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("下载完成！");
        es.shutdown();
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        return outputStream;
    }

    public byte[] getBytes() {
        return bytes;
    }

    private void setBlockSize(String fileUrl) throws IOException {
        URL               url            = new URL(fileUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestProperty("Connection", "close");
        int responseCode = httpConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            fileSize = httpConnection.getContentLength();
            System.out.println(fileUrl + "\n文件大小: " + fileSize / 1024 + " KB");
            blockSize = fileSize / threadNum;
            while (blockSize * threadNum < fileSize) {
                blockSize++;
            }
        }
    }

    private HttpURLConnection connect(int start, int end) throws IOException {
        URL               url  = new URL(this.url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(60 * 1000); // 60秒超时
        conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
        conn.connect();
        return conn;
    }


    public void showProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    /**
     * 手动释放内存
     */
    public void release() {
        bytes = null;
    }

    @Override
    public void close() throws IOException {
        bytes = null;
    }


    public static byte[] downloadBytes(String url){
        try (MultiThreadDownload downloader = new MultiThreadDownload(url)) {
            downloader.showProgress(true);
            downloader.download();
            byte[] bytes = downloader.getBytes();
            if (bytes == null || bytes.length == 0) {
                System.out.println("下载失败");
                return null;
            }
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}