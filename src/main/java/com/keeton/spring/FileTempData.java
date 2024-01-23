package com.keeton.spring;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author wys
 * @since 2022-08-11 10:53:22
 */
@Data
@Builder
public class FileTempData {

    /**
     * 文件id
     */
    String id;

    /**
     * 文件名
     */
    String name;

    /**
     * 文件大小
     */
    Long fileSize;

    /**
     * 文件在压缩包中的路径
     */
    String zipPath;

    /**
     * 文件路径（minio中的路径）
     */
    String path;

    /**
     * bucket
     */
    String bucket;

    /**
     * 文件的http地址
     */
    String url;

    /**
     * 是否为文件
     */
    boolean isFile;

    /**
     * 文件字节数组
     */
    byte[] data;

    /**
     * 创建日期
     */
    Date createTime;
}
