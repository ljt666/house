package com.ljt.house.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 租客dto
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/25 19:52
 */
@Data
public class UserListDto {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 退租申请表id
     */
    private Long aid;

    /**
     * 已退租列表id
     */
    private Long cid;

    /**
     * 租客姓名
     */
    private String name;

    /**
     * 用户登录名
     */
    private String username;

    private String idCard;

    private String phone;

    private String houseCode;

    private String address;

    private BigDecimal price;

    private String status;

}
