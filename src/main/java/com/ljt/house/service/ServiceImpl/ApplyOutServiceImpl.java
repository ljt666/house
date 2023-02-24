package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ljt.house.domain.*;
import com.ljt.house.dto.ApplyOutDto;
import com.ljt.house.persistence.*;
import com.ljt.house.service.ServiceInterface.ApplyOutService;
import com.ljt.house.service.ServiceInterface.HouseListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import com.ljt.house.util.UtilEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 退租申请业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/8 21:07
 */
@Service
public class ApplyOutServiceImpl extends ServiceImpl<ApplyOutMapper, ApplyOut> implements ApplyOutService {

    @Autowired
    private ApplyOutMapper applyoutMapper;
    @Autowired
    private HouseListMapper houselistMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private CheckoutMapper checkoutMapper;
    @Autowired
    private ZuListMapper zulistMapper;
    @Autowired
    private HouseListService houseListService;
    /**
     * @param zulist
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertApplyOut(ZuList zulist) throws ServiceException {
        ApplyOut applyout = ApplyOut.builder()
                .houseCode(zulist.getHouseCode())
                .address(zulist.getAddress())
                .status(UtilEnum.ApplyOutStatus3.getDescription())
                .userlistId(zulist.getUserlistId()).build();
        if (StringUtils.isEmpty(applyout)) {
            throw new ServiceException(RespCode.LEASEBACK_APPLICATION_CANNOT_BE_EMPTY);
        }
        applyoutMapper.insert(applyout);
    }

    @Override
    public List<ApplyOutDto> findAllApplyOut() {
        List<ApplyOutDto> list = applyoutMapper.findAllApplyOut();
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateApplyOut(ApplyOut applyout) throws ServiceException {
        if (StringUtils.isEmpty(applyout)) {
            throw new ServiceException(RespCode.LEASEBACK_APPLICATION_CANNOT_BE_EMPTY);
        }
        applyoutMapper.updateById(applyout);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void agreeApplyOut(Long id) throws ServiceException {
        ApplyOut applyout = applyoutMapper.selectById(id);
        if (StringUtils.isEmpty(applyout)) {
            throw new ServiceException(RespCode.LEASEBACK_APPLICATION_CANNOT_BE_EMPTY);
        }
        HouseList houseList = houseListService.selectOne(new EntityWrapper<HouseList>().eq("house_code", applyout.getHouseCode()));
        if (StringUtils.isEmpty(houseList)) {
            throw new ServiceException(RespCode.HOUSING_INFORMATION_CAN_NOT_BE_EMPTY);
        }
        houseList.setStatus(UtilEnum.houseListStatus1.getDescription());
        houselistMapper.updateById(houseList);
        EntityWrapper<Contract> wrapper = new EntityWrapper<>();
        wrapper.eq("house_code", applyout.getHouseCode());
        contractMapper.delete(wrapper);
        Checkout checkout = Checkout.builder()
                .houseCode(applyout.getHouseCode())
                .address(applyout.getAddress())
                .status(RespCode.CHECK_OUT_STATUS_RETIRED_TENNANCY)
                .userlistId(applyout.getUserlistId()).build();
        if (StringUtils.isEmpty(checkout)) {
            throw new ServiceException(RespCode.RENT_REFUNDED_LIST_CANNOT_BE_EMPTY);
        }
        checkoutMapper.insert(checkout);
        applyout.setStatus(UtilEnum.ApplyOutStatus1.getDescription());
        applyoutMapper.updateById(applyout);
        zulistMapper.delete(new EntityWrapper<ZuList>().eq("house_code", applyout.getHouseCode()));
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteApplyOut(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        }
        applyoutMapper.deleteById(id);
    }
}
