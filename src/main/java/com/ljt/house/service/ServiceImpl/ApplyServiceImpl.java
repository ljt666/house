package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ljt.house.domain.Apply;
import com.ljt.house.domain.HouseList;
import com.ljt.house.dto.ApplyDto;
import com.ljt.house.persistence.ApplyMapper;
import com.ljt.house.persistence.HouseListMapper;
import com.ljt.house.service.ServiceInterface.ApplyService;
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
 * @description: 看房申请业务逻辑层实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/23 09:45
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private HouseListMapper houselistMapper;
    @Autowired
    private HouseListService houseListService;

    /**
     * 获取基本不带条件的EntityWrapper对象
     */
    public EntityWrapper<Apply> getInitWrapper() {
        EntityWrapper<Apply> initWrapper = new EntityWrapper<>();
        return initWrapper;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertApply(Apply apply) throws ServiceException {
        if (StringUtils.isEmpty(apply)) {
            throw new ServiceException(RespCode.APPLICATION_FOR_HOUSE_VIEWING_CANNOT_BE_EMPTY);
        } else {
            applyMapper.insert(apply);
        }
    }

    @Override
    public List<ApplyDto> findApplylist() {
        List<ApplyDto> applyList = applyMapper.findApplylist();
        if (CollectionUtils.isEmpty(applyList)) {
            return new ArrayList<>();
        }
        return applyList;
    }

    @Override
    public Apply findByHouseCode(String houseCode) {
        Apply apply = this.selectOne(getInitWrapper().eq("house_code", houseCode));
        if (!StringUtils.isEmpty(apply)) {
            return apply;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteByHouseCode(String houseCode) throws ServiceException {
        if (houseCode == null) {
            throw new ServiceException(RespCode.HOUSECODE_IS_NOT_NULL);
        }
        applyMapper.delete(getInitWrapper().eq("house_code", houseCode));
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void refuseApply(String houseCode) throws ServiceException {
        //根据申请看房实体类中的房屋状态获取该条数据wrapper对象
        Wrapper<HouseList> wrapper = new EntityWrapper<HouseList>().eq(houseCode != null, "house_code", houseCode);
        //根据wrapper获取点击拒绝租赁的该条房源信息
        HouseList houselist = houseListService.selectOne(wrapper);
        if (StringUtils.isEmpty(houselist)) {
            throw new ServiceException(RespCode.HOUSING_INFORMATION_CAN_NOT_BE_EMPTY);
        }
        //设置房源状态为未租赁
        houselist.setStatus(UtilEnum.houseListStatus1.getDescription());
        //更新房源信息，更新状态为未租赁
        houselistMapper.update(houselist, wrapper);
        //根据申请看房实体类中的房屋状态删除该条申请看房数据
        applyMapper.delete(getInitWrapper().eq(houseCode != null, "house_code", houseCode));
    }
}
