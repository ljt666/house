package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljt.house.domain.User;
import com.ljt.house.persistence.UserMapper;
import com.ljt.house.service.ServiceInterface.UserService;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 用户业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/5 10:05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password, String type) {
//        User user1 = userMapper.selectByUser(username, password, type);
//        return user1;
       //这种写法更简便，不用写xml文件
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(username != null, "username", username).eq(password != null, "password", password).eq(type != null, "type", type);

        return this.selectOne(wrapper);

    }

    @Override
    public User findUserByUsername(String username) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(username != null, "username", username);
        User user = this.selectOne(wrapper);
        return user;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void register(String username, String password1, String type) {
        User user = User.builder()
                .username(username)
                .password(password1)
                .type(type).build();
        //userMapper.insert(user);
        //都可以
        this.insert(user);
    }
}
