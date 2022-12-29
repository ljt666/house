package com.ljt.house.dto;

import lombok.Data;

/**
 * @description: 已退租dto
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/31 13:36
 */
@Data
public class CheckoutDto {

    private Long id;

    private String houseCode;

    private String address;

    private String status;

    private String name;

    private String idCard;

    private String phone;
}
