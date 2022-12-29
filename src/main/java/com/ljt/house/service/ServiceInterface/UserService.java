package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.User;

/**
 * @description: 用户业务逻辑接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/5 10:03
 */
public interface UserService extends IService<User> {

    User login(String username, String password, String type);

    User findUserByUsername(String username);

    void register(String username, String password1, String type);
}
