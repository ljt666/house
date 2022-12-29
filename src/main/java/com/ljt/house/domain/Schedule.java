package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @description: 日程实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/4/2 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "schedule")
public class Schedule {

    @TableId
    private Long id;

    /**
     * 日期
     */
    @TableField(value = "date")
    private Date date;

    /**
     * 日程
     */
    @TableField(value = "content")
    private String content;
}
