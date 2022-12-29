package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.UserList;
import com.ljt.house.dto.UserListDto;

import java.util.List;

/**
 * @description: 租客信息dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/6 15:20
 */
public interface UserListMapper extends BaseMapper<UserList> {
    List<UserListDto> getUserZuList(Long id);

    List<UserListDto> getMyCheckout(Long id);

    List<UserListDto> getMyApply(Long id);

    List<UserListDto> getMyApplyOut(Long id);

    List<UserListDto> findAllUserList();
}
