package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload;

import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:33
 * @Description: 抽象文件上传接口
 */
@Component
public class ObjectUpload {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UploadFactory uploadFactory;

    /**
     * 配置上传方式 ：
     * 1、qiniuyun
     * 2、oss
     * 3、minio
     */
    @Value("${methodName}")
    private String methodName;

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    public Object upLoadFile(MultipartFile multipartFile) {
        return uploadFactory.getUploadMethod(methodName).upLoadFile(multipartFile);
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @return url 返回文件下载路径
     */
    public Resource download(String fileName) {
        return resourceService.getResource(fileName);
    }

}
