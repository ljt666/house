package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @description: 租客实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/6 15:00
 */
@Data
@TableName("userlist")
public class UserList {

    @TableId
    private Long id;

    /**
     * 租客姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 租客身份证号
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 租客电话号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

}
