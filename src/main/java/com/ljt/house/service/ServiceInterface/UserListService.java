package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.UserList;
import com.ljt.house.dto.UserListDto;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 租客信息业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/6 15:15
 */
public interface UserListService extends IService<UserList> {
    UserList findHouseList(Long userId);

    UserList checkUserList(String idCard);

    void insertUserList(UserList userlist) throws ServiceException;

    void updateUserList(UserList userlist) throws ServiceException;

    UserList findUserListUpdate(UserList userlist);

    List<UserListDto> getUserZuList(Long id);

    List<UserListDto> getMyCheckout(Long id);

    List<UserListDto> getMyApply(Long id);

    List<UserListDto> getMyApplyOut(Long id);

    List<UserListDto> findAllUserList();

    void deleteUserList(Long id) throws ServiceException;
}
