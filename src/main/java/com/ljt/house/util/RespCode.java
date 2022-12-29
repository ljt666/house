package com.ljt.house.util;

/**
 * @description: 其它代码值
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/4/14 20:53
 */
public interface RespCode {
    String Apply_Status_Application = "申请中";//申请看房状态
    String CHECK_OUT_STATUS_RETIRED_TENNANCY = "已退租";//已退租列表状态
    String HOUSE_CODE_EXIST = "该房屋代号已存在，添加失败";//房屋代号已存在，添加失败
    String HOUSE_IS_NOT_EXIST = "该房屋不存在，修改失败";//该房屋不存在
    String ADD_SUCCESS = "添加成功";//添加成功
    String UPDATE_SUCCESS = "更新成功";//更新成功
    String RENT_PAID = "租金已缴";//租金已缴
    String UNPAID_RENT = "租金未缴";//租金未缴
    String SUCCESSFUL_COMPLETION_OF_DATA = "资料完善成功";//资料完善成功
    String ID_CARD_IS_BOUND = "该身份证已被绑定,一个身份证号码只能被一个账户绑定！";//身份证绑定
    String ID_CARD_ID_BOUND2 = "该身份证号码已被绑定";//身份证绑定
    String NO_ZUKE_INFORMATION = "沒有匹配到租客信息";
    String SOLVE = "已处理";//已处理报障
    String PENDING_DISPOSAL = "待处理";//待处理报障
    String LEASEBACK_APPLICATION_CANNOT_BE_EMPTY = "退租申请不能为空";//退租申请不能为空
    String HOUSING_INFORMATION_CAN_NOT_BE_EMPTY = "房源信息不能为空";//房源信息不能为空
    String RENT_REFUNDED_LIST_CANNOT_BE_EMPTY = "已退租列表不能为空";//已退租列表不能为空
    String ID_IS_NOT_NULL = "id不能为空";
    String APPLICATION_FOR_HOUSE_VIEWING_CANNOT_BE_EMPTY = "申请看房不能为空";//申请看房不能为空
    String HOUSECODE_IS_NOT_NULL = "房源代码不能为空";
    String CONTRACT_IS_NOT_NULL = "合同信息不能为空";
    String CONTRACT_IS_NOT_EXIST = "该合同信息不存在";
    String SCHEDULE_IS_NOT_NULL = "日程不能为空";
    String WRONG_IS_NOT_NULL = "报障信息不能为空";
    String TOPAID_IS_NOT_NULL = "待缴租金信息不能为空";
    String USERLIST_IS_NOT_NULL = "租客信息不能为空";
    String USER_NAME_IS__NOT_EXIST = "该用户名不存在，请重新登录";
    String USER_NAME_IS_EXIST = "该用户名已存在";
    String PASSWORD_NOT_RIGHT = "密码输入错误，请重新输入";
    String TYPE_SELECT_ERROR = "类型选择错误";
    String REGISTER_SUCCESS = "注册成功，登录后请及时绑定账户信息";
}
