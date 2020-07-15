package com.shaojie.spring.cloud.alibaba.nacos.file.upload.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo.ResourceVo;

/**
 * @author ShaoJie
 * @Date 2020年07月15 14:39
 * @Description:
 */
public interface ResourceService {

    /**
     * 保存文件
     *
     * @param resourceVo 文件
     */
    void save(ResourceVo resourceVo);

    /**
     * 根据文件名称查询
     *
     * @param fileName 文件名
     * @return
     */
    Resource getResource(String fileName);

    /**
     * 查询所有文件
     *
     * @param objectPage 翻页信息
     * @param resourceVo 文件
     * @return
     */
    IPage<Resource> listResources(Page<Resource> objectPage, ResourceVo resourceVo);
}
