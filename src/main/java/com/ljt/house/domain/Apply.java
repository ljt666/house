package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description: 看房申请实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/23 09:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("apply")
public class Apply {

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
     * 房源面积
     */
    @TableField(value = "area")
    private double area;

    /**
     * 房源价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 租客id
     */
    @TableField(value = "userlist_id")
    private Long userlistId;

    /**
     * 房源状态(申请中)
     */
    @TableField(value = "status")
    private String status;

}
