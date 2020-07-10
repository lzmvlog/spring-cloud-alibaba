package com.shaojie.spring.cloud.alibaba.nacos.user.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ShaoJie
 * @Date 2020年07月10 15:24
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserVo {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String iphone;

}
