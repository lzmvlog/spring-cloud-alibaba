package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.minlo;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo.ResourceVo;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.ResourceService;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.AbstractUpload;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.util.Utils;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio文件上传
 *
 * @author chenghao
 * @createTime 2020/7/16
 */
@Service
@Slf4j
public class MinIoUpLoad extends AbstractUpload {

    /**
     * URL
     */
    @Value("${minlo.endpoint}")
    private String endpoint;

    /**
     * 账号标识
     */
    @Value("${minlo.access.key}")
    private String accessKey;

    /**
     * 账号密码
     */
    @Value("${minlo.secret.key}")
    private String secretKey;

    /**
     * 存储桶名称
     */
    @Value("${minlo.bucket.name}")
    private String bucket;

    @Autowired
    private ResourceService resourceService;

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @param bucketName    存储桶名称
     * @return 上传完成：文件相关信息；上传失败：错误信息
     */
    @SneakyThrows
    @SuppressWarnings("all")
    @Override
    public String upLoadFile(MultipartFile multipartFile) {
        // 判断传入参数是否是空文件
        if (multipartFile.isEmpty() || multipartFile.getSize() <= 0) {
            return "not allowed null file";
        }
        // 判断传入参数是否空值
        if (StrUtil.hasBlank(bucket)) {
            return "bucketName not allowed null";
        }

        String url = null;
        try {
            // 创建客户端对象
            MinioClient client = new MinioClient(endpoint, accessKey, secretKey);
            // 判断存储桶是否存在
            if (!client.bucketExists(bucket)) {
                // 创建不存在的存储桶
                client.makeBucket(bucket);
                log.info("已创建不存在的存储桶：{}", bucket);
            }
            // 解决可能会出现相同新文件名，重新定义文件名 + 文件后缀
            String newFileName = String.format("%s.%s", IdUtil.fastSimpleUUID(), Utils.getSuffix(multipartFile.getOriginalFilename()));
            url = String.format("%s%s/%s", endpoint, bucket, newFileName);

            // 上传文件
            client.putObject(bucket, newFileName, multipartFile.getInputStream(), new PutObjectOptions(multipartFile.getSize(), -1));
            // 保存文件
            resourceService.save(new ResourceVo().setUrl(url).setFileName(multipartFile.getOriginalFilename()));
            return url;
        } catch (InvalidEndpointException e) {
            log.error("无效端点异常：{0}", e);
        } catch (InvalidPortException e) {
            log.error("无效端口异常：{0}", e);
        }
        return "upLoad file error";

    }

}
