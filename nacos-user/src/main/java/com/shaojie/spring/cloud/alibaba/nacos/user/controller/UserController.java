package com.shaojie.spring.cloud.alibaba.nacos.user.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaojie.spring.cloud.alibaba.nacos.user.model.vo.UserVo;
import com.shaojie.spring.cloud.alibaba.nacos.user.service.impl.UserServiceImpl;
import com.shaojie.spring.cloud.alibaba.nacos.user.util.PageUtil;
import com.shaojie.spring.cloud.alibaba.nacos.user.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShaoJie
 * @Date 2020年07月10 14:34
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserServiceImpl userService;

    /**
     * 保存
     *
     * @param userVo user 视图
     * @return String 返回信息
     */
    @PostMapping("save")
    public Response saveUser(UserVo userVo) {
        userService.saveUser(userVo);
        return new Response(HttpStatus.HTTP_OK, "新增成功");
    }

    /**
     * 查询用户
     *
     * @param userVo   user 视图
     * @param pageUtil 查询页码
     * @return IPage 用户集合
     */
    @GetMapping("list")
    public Response listUsers(UserVo userVo, PageUtil pageUtil) {
        return new Response(HttpStatus.HTTP_OK, userService.listUsers(new Page<>(pageUtil.getPage(), pageUtil.getPageNum()), userVo));
    }

}
