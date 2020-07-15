package com.shaojie.spring.cloud.alibaba.nacos.file.upload.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ShaoJie
 * @Date 2020年07月15 14:32
 * @Description:
 */
@Mapper
public interface ResourceDao extends BaseMapper<Resource> {
}
