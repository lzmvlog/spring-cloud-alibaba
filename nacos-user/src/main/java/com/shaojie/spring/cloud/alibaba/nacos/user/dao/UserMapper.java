package com.shaojie.spring.cloud.alibaba.nacos.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shaojie.spring.cloud.alibaba.nacos.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ShaoJie
 * @Date 2020年07月10 14:31
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
