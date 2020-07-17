package com.shaojie.spring.cloud.alibaba.nacos.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShaoJie
 * @Date 2020年07月09 10:45
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosUserApplication.class, args);
    }
}
