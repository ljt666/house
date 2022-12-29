package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 退租申请实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/8 21:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("applyout")
public class ApplyOut {

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
     * 退租申请状态(已拒绝/申请中/已同意)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 租客id
     */
    @TableField(value = "userlist_id")
    private Long userlistId;
}
