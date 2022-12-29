package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description: 合同实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/28 13:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "contract")
public class Contract {

    @TableId
    private Long id;

    /**
     * 房东姓名
     */
    @TableField(value = "chuzu")
    private String chuzu;

    /**
     * 房东身份证号
     */
    @TableField(value = "chuzu_id_card")
    private String chuzuIdCard;

    /**
     * 租客姓名
     */
    @TableField(value = "zuke")
    private String zuke;

    /**
     * 租客身份证号
     */
    @TableField(value = "zuke_id_card")
    private String zukeIdCard;

    /**
     * 租房日期从
     */
    @TableField(value = "date_from")
    private Date dateFrom;

    /**
     * 租房日期至
     */
    @TableField(value = "date_to")
    private Date dateTo;

    /**
     * 租房价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 租房地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 房源代码
     */
    @TableField(value = "house_code")
    private String houseCode;

    /**
     * 交租日
     */
    @TableField(value = "pay_day")
    private Integer payDay;
}
