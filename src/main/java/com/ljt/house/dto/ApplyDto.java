package com.ljt.house.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 申请看房dto
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/31 13:30
 */
@Data
public class ApplyDto {

    private Long id;

    private String houseCode;

    private String address;

    private double area;

    private BigDecimal price;

    private String name;

    private String idCard;

    private String phone;
}
