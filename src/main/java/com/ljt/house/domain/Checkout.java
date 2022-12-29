package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 已退租实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/13 12:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "checkout")
public class Checkout {

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
     * 状态(已退租)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 租客id
     */
    @TableField(value = "userlist_id")
    private Long userlistId;
}
