package com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.dao.ResourceDao;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo.ResourceVo;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShaoJie
 * @Date 2020年07月15 14:39
 * @Description:
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    /**
     * 保存文件
     *
     * @param resourceVo 文件
     */
    @Override
    public void save(ResourceVo resourceVo) {
        resourceDao.insert(new Resource().setId(IdUtil.fastSimpleUUID())
                .setFileName(resourceVo.getFileName())
                .setUrl(resourceVo.getUrl())
                .setCreateime(DateUtil.date()));
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @return
     */
    @Override
    public Resource getResource(String fileName) {
        if (fileName == null) {
            throw new RuntimeException("查询的文件名称不能为空");
        }

        return resourceDao.selectOne(Wrappers.query(new Resource().setFileName(fileName)));
    }

    /**
     * 查询所有文件
     *
     * @param objectPage 翻页信息
     * @param resourceVo 文件
     * @return
     */
    @Override
    public IPage<Resource> listResources(Page<Resource> objectPage, ResourceVo resourceVo) {
        return resourceDao.selectPage(objectPage, Wrappers.query(new Resource()
                .setFileName(resourceVo.getFileName())));
    }
}