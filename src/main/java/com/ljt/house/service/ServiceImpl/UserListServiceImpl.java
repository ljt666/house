package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljt.house.domain.UserList;
import com.ljt.house.dto.UserListDto;
import com.ljt.house.persistence.UserListMapper;
import com.ljt.house.persistence.UserMapper;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 租客信息业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/6 15:15
 */
@Service
@Transactional
public class UserListServiceImpl extends ServiceImpl<UserListMapper, UserList> implements UserListService {

    @Autowired
    private UserListMapper userlistMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取基本不带条件的EntityWrapper对象
     */
    public EntityWrapper<UserList> getInitWrapper() {
        EntityWrapper<UserList> initWrapper = new EntityWrapper<>();
        return initWrapper;
    }

    @Override
    public UserList findHouseList(Long userId) {
        Wrapper<UserList> wrapper = getInitWrapper().eq(userId != null, "user_id", userId);
        UserList userlist = this.selectOne(wrapper);
        if (!StringUtils.isEmpty(userlist)) {
            return userlist;
        }
        return null;
    }

    @Override
    public UserList checkUserList(String idCard) {
        Wrapper<UserList> wrapper = getInitWrapper().eq(idCard != null, "id_card", idCard);
        UserList userlist = this.selectOne(wrapper);
        if (!StringUtils.isEmpty(userlist)) {
            return userlist;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertUserList(UserList userlist) throws ServiceException {
        if (StringUtils.isEmpty(userlist)) {
            throw new ServiceException(RespCode.USERLIST_IS_NOT_NULL);
        }
        userlistMapper.insert(userlist);
    }

    @Override
    public void updateUserList(UserList userlist) throws ServiceException {
        if (StringUtils.isEmpty(userlist)) {
            throw new ServiceException(RespCode.USERLIST_IS_NOT_NULL);
        }
        userlistMapper.updateById(userlist);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public UserList findUserListUpdate(UserList userlist) {
        Wrapper<UserList> wrapper = getInitWrapper()
                .eq(userlist.getId() != null, "id", userlist.getId())
                .eq(userlist.getIdCard() != null, "id_card", userlist.getIdCard());
        UserList userList = this.selectOne(wrapper);
        if (StringUtils.isEmpty(userList)) {
            return null;
        }
        return userList;
    }

    @Override
    public List<UserListDto> getUserZuList(Long id) {
        List<UserListDto> userlist = userlistMapper.getUserZuList(id);
        if (!CollectionUtils.isEmpty(userlist)) {
            return userlist;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserListDto> getMyCheckout(Long id) {
        List<UserListDto> list = userlistMapper.getMyCheckout(id);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserListDto> getMyApply(Long id) {
        List<UserListDto> list = userlistMapper.getMyApply(id);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserListDto> getMyApplyOut(Long id) {
        List<UserListDto> list = userlistMapper.getMyApplyOut(id);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserListDto> findAllUserList() {
        List<UserListDto> list = userlistMapper.findAllUserList();
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteUserList(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        } else {
            //删除该用户绑定租客身份信息
            userlistMapper.delete(getInitWrapper().eq("user_id", id));
            //删除该用户信息
            userMapper.deleteById(id);
        }
    }
}
