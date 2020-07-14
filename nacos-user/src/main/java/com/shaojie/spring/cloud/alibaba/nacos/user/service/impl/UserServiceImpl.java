package com.shaojie.spring.cloud.alibaba.nacos.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaojie.spring.cloud.alibaba.nacos.user.dao.UserMapper;
import com.shaojie.spring.cloud.alibaba.nacos.user.model.User;
import com.shaojie.spring.cloud.alibaba.nacos.user.model.vo.UserVo;
import com.shaojie.spring.cloud.alibaba.nacos.user.service.UserService;
import com.shaojie.spring.cloud.alibaba.nacos.user.util.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShaoJie
 * @Date 2020年07月10 14:22
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    /**
     * 保存
     *
     * @param userVo 用户信息
     */
    @Override
    public void saveUser(UserVo userVo) {
        userMapper.insert(new User().setId(IdUtil.fastSimpleUUID())
                .setUserName(userVo.getUserName())
                .setAccount(userVo.getAccount())
//                .setAvatar()
                .setIphone(userVo.getIphone())
                .setIsStatus(StatusEnum.NONE.getValue()));
    }

    /**
     * 查询用户信息
     *
     * @param page   页码
     * @param userVo user 视图 信息
     * @return
     */
    @Override
    public IPage<User> listUsers(Page<User> page, UserVo userVo) {
        return userMapper.selectPage(page, Wrappers.query(new User()
                .setId(userVo.getId())
                .setUserName(userVo.getUserName())
                .setAccount(userVo.getAccount())
                .setIphone(userVo.getIphone())
        ));
    }

}
