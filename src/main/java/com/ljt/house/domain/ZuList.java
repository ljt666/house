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
 * @description: 在租列表实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/31 10:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("zulist")
public class ZuList {

    @TableId
    private Long id;

    /**
     * 房源代码
     */
    @TableField(value = "house_code")
    private String houseCode;

    /**
     * 房源价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 房源地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 租客id
     */
    @TableField(value = "userlist_id")
    private Long userlistId;

    /**
     * 合同id
     */
    @TableField(value = "contract_id")
    private Long contractId;
}
