package com.ljt.house.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 在租列表dto
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/31 13:58
 */
@Data
public class ZulistDto {

    private Long id;

    private String houseCode;

    private BigDecimal price;

    private String address;

    private String name;

    private String idCard;

    private String phone;

    private Long userlistId;
}
