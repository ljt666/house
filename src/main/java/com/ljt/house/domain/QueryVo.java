package com.ljt.house.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 自定义条件查询实体类(作为条件搜索栏实体类传入,并且model.add去传给前端)
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/13 11:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryVo {

    private String zuname;

    private String dateFrom;

    private String dateTo;

    private Long userlistId;

    private String houseCode;

    private String address;

    private String priceFrom;

    private String priceTo;
}
