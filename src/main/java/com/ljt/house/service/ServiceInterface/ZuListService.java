package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.ZuList;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 在租列表业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/31 10:33
 */
public interface ZuListService extends IService<ZuList> {
    void insertZuList(ZuList zulist);

    List<ZulistDto> findZuUserList();

    ZuList findZuList(String houseCode);

    void deleteZuList(String houseCode) throws ServiceException;

    List<ZulistDto> findZuListByUid(Long userlistId);

    ZuList findZulistById(Long id);
}
