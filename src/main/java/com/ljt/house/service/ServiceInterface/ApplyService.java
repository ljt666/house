package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.Apply;
import com.ljt.house.dto.ApplyDto;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 看房申请业务逻辑层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/23 09:45
 */
public interface ApplyService extends IService<Apply> {
    void insertApply(Apply apply) throws ServiceException;

    List<ApplyDto> findApplylist();

    Apply findByHouseCode(String houseCode);

    void deleteByHouseCode(String houseCode) throws ServiceException;

    void refuseApply(String houseCode) throws ServiceException;
}
