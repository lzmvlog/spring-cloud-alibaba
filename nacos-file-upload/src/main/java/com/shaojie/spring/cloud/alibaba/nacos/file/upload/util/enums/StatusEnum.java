package com.shaojie.spring.cloud.alibaba.nacos.file.upload.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ShaoJie
 * @Date 2020年07月10 15:42
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    NONE(1, "正常"),
    DELETE(0, "删除");

    /**
     * 数据库保存值
     */
    private Integer value;

    /**
     * 描述
     */
    private String description;

}
