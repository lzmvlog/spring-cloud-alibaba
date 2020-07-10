package com.shaojie.spring.cloud.alibaba.nacos.user.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ShaoJie
 * @Date 2020年07月03 14:33
 * @Description:
 */
@Data
@AllArgsConstructor
public class PageUtil {

    private Integer page = 1;

    private Integer pageNum = 10;

    public Integer getPage() {
        if ("".equals(page) || page == null) {
            return 1;
        }
        return page;
    }

    public Integer getPageNum() {
        if ("".equals(pageNum) || pageNum == null) {
            return 10;
        }
        return pageNum;
    }
}
