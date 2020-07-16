package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.oss;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo.ResourceVo;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.ResourceService;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.AbstractUpload;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author ShaoJie
 * @Date 2020年07月16 11:05
 * @Description: oss 文件上传
 */
@Service
@Slf4j
public class OSSUpload extends AbstractUpload {

    @Value("${oss.access.key}")
    private String accessKey;

    @Value("${oss.secret.key}")
    private String secretKey;

    @Value("${oss.bucket.name}")
    private String bucket;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.domainName}")
    private String domainName;

    @Autowired
    private ResourceService resourceService;

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    @Override
    public String upLoadFile(MultipartFile multipartFile) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
        String resourceId = IdUtil.fastSimpleUUID();
        String url = null;
        try {
            String file = String.format("%s/%s.%s", DateUtil.today(), resourceId, Utils.getSuffix(multipartFile.getOriginalFilename()));
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, file, new ByteArrayInputStream(multipartFile.getBytes()));
            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            // 保存文件
            url = String.format("%s/%s", domainName, file);

            resourceService.save(new ResourceVo().setUrl(url).setFileName(multipartFile.getOriginalFilename()));
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (IOException e) {
            log.error("oss 上传失败 e:{}", e);
            throw new RuntimeException("上传失败");
        }
        return null;
    }

}
