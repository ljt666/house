package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @description: 报障实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/2 12:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wrong")
public class Wrong {

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
     * 报障日期
     */
    @TableField(value = "date")
    private Date date;

    /**
     * 报障内容
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 租赁人姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 租客id
     */
    @TableField(value = "userlist_id")
    private Long userlistId;

    /**
     * 状态(待处理)
     */
    @TableField(value = "status")
    private String status;
}
