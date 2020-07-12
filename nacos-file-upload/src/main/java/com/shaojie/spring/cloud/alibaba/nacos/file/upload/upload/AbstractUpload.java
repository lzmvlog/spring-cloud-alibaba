package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload;

import com.qiniu.http.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:33
 * @Description: 抽象文件上传接口
 */
public abstract interface AbstractUpload {

    /**
     * 上传文件
     *
     * @param files 文件字节
     * @return
     */
    public abstract String upLoadFile(byte[] files);


    /**
     * 上传文件
     *
     * @param stream 文件流
     * @return
     */
    public abstract String upLoadFile(ByteArrayInputStream stream);

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    public abstract Response upLoadFile(MultipartFile multipartFile);

}
