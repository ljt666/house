package com.ljt.house.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @description: 房源列表实体类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/18 20:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "houselist")
public class HouseList {

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
     * 房子面积
     */
    @TableField(value = "area")
    private double area;

    /**
     * 房子价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 房源状态(未租赁/已租赁/已被申请)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 房子户型
     */
    @TableField(value = "apartment_layout")
    private String apartmentLayout;

    /**
     * 图片
     */
    @TableField(value = "picture")
    private byte[] picture;

    @TableField(exist = false)
    private MultipartFile file;

    @TableField(exist = false)
    private StringBuffer encode;
}
