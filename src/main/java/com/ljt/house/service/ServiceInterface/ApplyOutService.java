package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.ApplyOut;
import com.ljt.house.domain.ZuList;
import com.ljt.house.dto.ApplyOutDto;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 退租申请业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/8 21:07
 */
public interface ApplyOutService extends IService<ApplyOut> {
    void insertApplyOut(ZuList zulist) throws ServiceException;

    List<ApplyOutDto> findAllApplyOut();

    void updateApplyOut(ApplyOut applyout) throws ServiceException;

    void agreeApplyOut(Long id) throws ServiceException;

    void deleteApplyOut(Long id) throws ServiceException;
}
