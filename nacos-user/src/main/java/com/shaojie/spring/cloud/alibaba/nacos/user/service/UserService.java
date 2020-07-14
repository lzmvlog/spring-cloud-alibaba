package com.shaojie.spring.cloud.alibaba.nacos.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaojie.spring.cloud.alibaba.nacos.user.model.User;
import com.shaojie.spring.cloud.alibaba.nacos.user.model.vo.UserVo;

/**
 * @author ShaoJie
 * @Date 2020年07月10 14:19
 * @Description:
 */
public interface UserService {

    /**
     * 保存
     *
     * @param userVo 用户信息
     */
    void saveUser(UserVo userVo);

    /**
     * 查询用户信息
     *
     * @param page   页码
     * @param userVo user 视图 信息
     * @return
     */
    IPage<User> listUsers(Page<User> page, UserVo userVo);

}
