package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.minio;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio文件上传
 *
 * @author chenghao
 * @createTime 2020/7/16
 */
@Slf4j
public class MinIoUpLoad {

    /**
     * URL
     */
    @Value("minio.endpoint")
    private String endpoint;

    /**
     * 账号标识
     */
    @Value("minio.accessKey")
    private String accessKey;

    /**
     * 账号密码
     */
    @Value("minio.secretKey")
    private String secretKey;


    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储桶名称
     * @return 上传完成：文件相关信息；上传失败：错误信息
     * @TODO 未定义上传成功、失败返回的信息
     */
    @SneakyThrows
    @SuppressWarnings("all")
    public String upLoadFile(MultipartFile file, String bucketName) {
        // 判断传入参数是否是空文件
        if (file.isEmpty() || file.getSize() <= 0) {
            return "not allowed null file";
        }
        // 判断传入参数是否空值
        if (StrUtil.hasBlank(bucketName)) {
            return "bucketName not allowed null";
        }
        try {
            // 创建客户端对象
            MinioClient client = new MinioClient(endpoint, accessKey, secretKey);
            // 判断存储桶是否存在
            if (!client.bucketExists(bucketName)) {
                // 创建不存在的存储桶
                client.makeBucket(bucketName);
                log.info("已创建不存在的存储桶：{}", bucketName);
            }
            // 解决可能会出现相同新文件名，重新定义文件名 + 文件后缀
            String newFileName = IdUtil.fastSimpleUUID()
                    + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            // 上传文件
            client.putObject(bucketName, newFileName, file.getInputStream(), new PutObjectOptions(file.getSize(), -1));
            return "upLoad file success";
        } catch (InvalidEndpointException e) {
            log.error("无效端点异常：{0}", e);
        } catch (InvalidPortException e) {
            log.error("无效端口异常：{0}", e);
        }
        return "upLoad file error";

    }

}
