package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.User;

/**
 * @description: 用户dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/5 10:07
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUser(String username, String password, String type);

}