package com.shaojie.spring.cloud.alibaba.nacos.user.controller;

import com.shaojie.spring.cloud.alibaba.nacos.user.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月12 22:48
 * @Description:
 */
@RestController
public class UpLoadFileController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public RestTemplate template;

    public Response uploadFile(String uuid, MultipartFile multipartFile) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-file-upload");
        String url = String.format("http://%s:%s/file/uploadFile/%s", serviceInstance.getHost(), serviceInstance.getPort(), multipartFile);
        template.getForObject(url, String.class);

        return null;
    }


}
