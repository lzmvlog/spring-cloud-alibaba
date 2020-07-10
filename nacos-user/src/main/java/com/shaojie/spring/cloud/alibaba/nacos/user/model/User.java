package com.shaojie.spring.cloud.alibaba.nacos.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ShaoJie
 * @Date 2020年07月09 17:44
 * @Description: 数据库用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_user")
public class User {

    /**
     * 用户id
     */
    @TableId("uuid")
    private String id;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户账号
     */
    @TableField("account")
    private String account;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 电话
     */
    @TableField("iphone")
    private String iphone;

    /**
     * 删除唯一标识
     * 1 存在
     * 0 删除
     */
    @TableField("is_status")
    private Integer isStatus;

}
