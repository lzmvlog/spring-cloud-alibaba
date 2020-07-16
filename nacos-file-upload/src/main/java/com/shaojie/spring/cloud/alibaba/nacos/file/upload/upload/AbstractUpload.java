package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload;

import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:33
 * @Description: 抽象文件上传接口
 */
public abstract class AbstractUpload {

    @Autowired
    private ResourceService resourceService;

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    public abstract Object upLoadFile(MultipartFile multipartFile);

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @return url 返回文件下载路径
     */
    public Resource download(String fileName){
        return  resourceService.getResource(fileName);
    }

}
