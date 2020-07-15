package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload;

import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:33
 * @Description: 抽象文件上传接口
 */
public abstract class AbstractUpload {

//    /**
//     * 上传文件
//     *
//     * @param files 文件字节
//     * @return
//     */
//    String upLoadFile(byte[] files);


//    /**
//     * 上传文件
//     *
//     * @param stream 文件流
//     * @return
//     */
//    String upLoadFile(ByteArrayInputStream stream);

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    public abstract String upLoadFile(MultipartFile multipartFile);

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @return url 返回文件下载路径
     */
    public abstract Resource download(String fileName);

}
