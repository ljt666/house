package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description: 待缴租金实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "topaid")
public class ToPaid {

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
     * 租客应缴日期
     */
    @TableField(value = "date")
    private Date date;

    /**
     * 租客应缴租金
     */
    @TableField(value = "price")
    private BigDecimal price;

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
     * 状态(租金未缴)
     */
    @TableField(value = "status")
    private String status;
}
