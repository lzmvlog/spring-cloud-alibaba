package com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload;

import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.minlo.MinIoUpLoad;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.oss.OSSUpload;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.upload.qiniuyun.QiUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author ShaoJie
 * @Date 2020年07月17 09:50
 * @Description: 上传工厂
 */
@Component
public class UploadFactory {

    /**
     * MINLO
     */
    @Autowired
    private MinIoUpLoad minIoUpLoad;

    /**
     * OSS
     */
    @Autowired
    private OSSUpload ossUpload;

    /**
     * 七牛云
     */
    @Autowired
    private QiUpload qiUpload;

    /**
     * 获取文件上传方式，实现不同上传的功能
     *
     * @param methodName 上传方式
     * @return UploadResource 上传请求方式
     */
    public UploadResource getUploadMethod(@NotNull String methodName) {
        // minlo 文件上传
        if (methodName.equals("minlo")) {
            return minIoUpLoad;
        }
        // oss 文件上传
        if (methodName.equals("oss")) {
            return ossUpload;
        }
        // 七牛云文件上传
        if (methodName.equals("qiniuyun")) {
            return qiUpload;
        }
        return null;
    }

}
