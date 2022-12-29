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
 * @description: 已缴租金实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "paid")
public class Paid {

    @TableId
    private Long id;

    /**
     * 房源代码
     */
    @TableField(value = "house_code")
    private String houseCode;

    /**
     * 房源地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 租金应缴日期
     */
    @TableField(value = "date")
    private Date date;

    /**
     * 房源价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 租金实缴日期
     */
    @TableField(value = "pay_date")
    private String payDate;

    /**
     * 租客姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 租客id
     */
    @TableField(value = "userlist_id")
    private Long userlistId;

    /**
     * 状态(租金已缴)
     */
    @TableField(value = "status")
    private String status;
}
