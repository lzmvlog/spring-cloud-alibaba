package com.shaojie.spring.cloud.alibaba.nacos.file.upload.controller;

import cn.hutool.http.HttpStatus;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.qiniuyun.QiUpload;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:43
 * @Description:
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private QiUpload qiUpLoad;

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     */
    @GetMapping("upload")
    public Response upload(@RequestParam("file") MultipartFile multipartFile) {
        return new Response(HttpStatus.HTTP_OK, qiUpLoad.upLoadFile(multipartFile));
    }

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     */
    @GetMapping("uploadFile/{file}")
    public Response upLoadFile(@PathVariable(value = "file") MultipartFile multipartFile) {
        qiUpLoad.upLoadFile(multipartFile);
        return null;
    }

    /**
     * 文件上传
     *
     * @return
     */
    @GetMapping("hello")
    public Response hello() {
//        qiUpLoad.upLoadFile(multipartFile);
        return new Response(HttpStatus.HTTP_OK, "成功");
    }

}
