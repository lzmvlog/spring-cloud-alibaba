package com.shaojie.spring.cloud.alibaba.nacos.file.upload.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo.ResourceVo;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.ResourceService;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.ObjectUpload;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.util.PageUtil;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:43
 * @Description:
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ObjectUpload abstractUpload;

    @Autowired
    private ResourceService resourceService;

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     */
    @GetMapping("upload")
    public Response upload(@RequestParam("file") MultipartFile multipartFile) {
        return new Response(HttpStatus.HTTP_OK, abstractUpload.upLoadFile(multipartFile));
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     * @return
     */
    @GetMapping("download")
    public Response download(String fileName) {
        return new Response(HttpStatus.HTTP_OK, abstractUpload.download(fileName));
    }

    /**
     * 查询用户
     *
     * @param ResourceVo   文件 视图
     * @param pageUtil 查询页码
     * @return IPage 用户集合
     */
    @GetMapping("list")
    public Response listUsers(ResourceVo ResourceVo, PageUtil pageUtil) {
        return new Response(HttpStatus.HTTP_OK, resourceService.listResources(new Page<Resource>(pageUtil.getPage(), pageUtil.getPageNum()), ResourceVo));
    }

}
