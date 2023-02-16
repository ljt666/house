package com.ljt.house.util;

/**
 * @description: 状态枚举类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/4/14 19:56
 */
public enum UtilEnum {

    houseListStatus1(1, "未租赁"),
    HouseListStatus2(2, "已租赁"),
    HouseListStatus3(3, "已被申请"),
    ApplyOutStatus1(4, "已同意"),
    ApplyOutStatus2(5, "已拒绝"),
    ApplyOutStatus3(6, "申请中"),
    //UserType1(7, "admin"),
    UserType2(8, "zuke");

    private Integer id;

    private String description;

    UtilEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
