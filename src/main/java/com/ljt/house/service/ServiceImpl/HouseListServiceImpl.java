package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ljt.house.domain.HouseList;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.persistence.HouseListMapper;
import com.ljt.house.service.ServiceInterface.HouseListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 房源列表业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/18 20:07
 */
@Service
public class HouseListServiceImpl extends ServiceImpl<HouseListMapper, HouseList> implements HouseListService {

    @Autowired
    private HouseListMapper houselistMapper;

    /**
     * 获取基本不带条件的EntityWrapper对象
     */
    public EntityWrapper<HouseList> getInitWrapper() {
        EntityWrapper<HouseList> initWrapper = new EntityWrapper<>();
        return initWrapper;
    }

    @Override
    public List<HouseList> selectHouselist(QueryVo vo) {
        List<HouseList> houselist = houselistMapper.selectHouselist(vo);
        if (CollectionUtils.isEmpty(houselist)) {
            return new ArrayList<>();
        }
        return houselist;
    }

    @Override
    public HouseList findByHouseCode(String houseCode) {
        HouseList houselist = this.selectOne(getInitWrapper().eq("house_code", houseCode));
        return houselist;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertHouse(HouseList houselist) throws ServiceException {
        if (StringUtils.isEmpty(houselist)) {
            throw new ServiceException(RespCode.HOUSING_INFORMATION_CAN_NOT_BE_EMPTY);
        }
        houselistMapper.insert(houselist);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteHouse(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        }
        houselistMapper.deleteById(id);
    }

    @Override
    public HouseList findByHouseCodeUpdate(HouseList houselist) {
        HouseList list = houselistMapper.findByHouseCodeUpdate(houselist);
        return list;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateHouse(HouseList houselist) throws ServiceException {
        if (StringUtils.isEmpty(houselist)) {
            throw new ServiceException(RespCode.HOUSING_INFORMATION_CAN_NOT_BE_EMPTY);
        }
        houselistMapper.updateById(houselist);
    }

    @Override
    public HouseList findById(Long id) {
        HouseList list = houselistMapper.selectById(id);
        if (!StringUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateHouseStatus(HouseList houselist) throws ServiceException {
        if (StringUtils.isEmpty(houselist)) {
            throw new ServiceException(RespCode.HOUSING_INFORMATION_CAN_NOT_BE_EMPTY);
        }
        houselistMapper.updateById(houselist);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteHouseByHouseCode(String houseCode) {
        houselistMapper.delete(getInitWrapper().eq(houseCode != null, "house_code", houseCode));
    }
}
