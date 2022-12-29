package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.HouseList;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 房源列表业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/18 20:06
 */
public interface HouseListService extends IService<HouseList> {
    List<HouseList> selectHouselist(QueryVo vo);

    HouseList findByHouseCode(String houseCode);

    void insertHouse(HouseList houselist) throws ServiceException;

    void deleteHouse(Long id) throws ServiceException;

    HouseList findById(Long id);

    HouseList findByHouseCodeUpdate(HouseList houselist);

    void updateHouse(HouseList houselist) throws ServiceException;

    void updateHouseStatus(HouseList houselist) throws ServiceException;

    void deleteHouseByHouseCode(String houseCode);
}
