package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.qiniuyun;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo.ResourceVo;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.impl.ResourceServiceImpl;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.AbstractUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:38
 * @Description:
 */
@Service
@Slf4j
public class QiUpload extends AbstractUpload {

    @Value("${qiniuyun.access.key}")
    private String accessKey;

    @Value("${qiniuyun.secret.key}")
    private String secretKey;

    @Value("${qiniuyun.bucket.name}")
    private String bucket;

    @Value("${qiniuyun.domainName}")
    private String domainName;

    @Autowired
    private ResourceServiceImpl resourceService;

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    @Override
    public String upLoadFile(MultipartFile multipartFile) {
        //构造一个带指定 Region 对象的配置类
        Configuration configuration = new Configuration(Region.region0());
        // 构建上传管理器
        UploadManager uploadManager = new UploadManager(configuration);
        Response response = null;
        try {
            if (multipartFile == null) {
                log.info("上传文件为空");
                throw new RuntimeException("上传文件为空");
            }

            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            Auth auth = Auth.create(accessKey, secretKey);
            // 上传的 token
            String upToken = auth.uploadToken(bucket);

            try {
                response = uploadManager.put(byteInputStream, IdUtil.fastSimpleUUID(), upToken, null, multipartFile.getContentType());
                //解析上传成功的结果
                DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                log.info("上传文件名 {} - 上传的hash {}", defaultPutRet.key, defaultPutRet.hash);
                // 保存文件
                resourceService.save(new ResourceVo().setFileName(multipartFile.getOriginalFilename()).setUrl(domainName + defaultPutRet.key));
            } catch (QiniuException ex) {
                log.error("qiniuyun - 上传出错 - {}", ex);
                throw new RuntimeException("qiniuyun - 上传出错");
            }
        } catch (IOException e) {
            log.error("qiniuyun - 文件 multipartFile 转换 byte[] 失败 - {}", e);
            throw new RuntimeException("qiniuyun - 文件 multipartFile 转换 byte[] 失败");
        }
        return "上传成功";
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @return
     */
    @Override
    public Resource download(String fileName) {
        return resourceService.getResource(fileName);
    }
}
