package com.shaojie.spring.cloud.alibaba.nacos.file.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShaoJie
 * @Date 2020年07月10 16:48
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosFileUploadApplication.class, args);
    }
}
