package com.shaojie.spring.cloud.alibaba.nacos.user.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShaoJie
 * @Date 2020年07月02 11:35
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * @param code 响应码
     * @param data 响应数据
     */
    public Response(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * @param code    响应码
     * @param message 响应信息
     */
    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @param code 响应码
     */
    public Response(Integer code) {
        this.code = code;
    }
}