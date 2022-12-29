package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/5 09:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "user")
public class User {

    @TableId
    private Long id;

    /**
     * 用户姓名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户类型(admin/zuke)
     */
    @TableField(value = "type")
    private String type;
}