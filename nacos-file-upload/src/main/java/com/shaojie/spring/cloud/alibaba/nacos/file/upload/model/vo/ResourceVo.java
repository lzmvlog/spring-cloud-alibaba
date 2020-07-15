package com.shaojie.spring.cloud.alibaba.nacos.file.upload.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ShaoJie
 * @Date 2020年07月15 14:29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_resource")
public class ResourceVo {

    /**
     * 文件 id
     */
    @TableField("id")
    private String id;

    /**
     * 文件 名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件存贮路径
     */
    @TableField("url")
    private String url;

    /**
     * 文件 id
     */
    @TableField("create_time")
    private Date createime;

}
