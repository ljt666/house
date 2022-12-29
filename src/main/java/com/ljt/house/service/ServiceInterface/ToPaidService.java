package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.Paid;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.ToPaid;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 待缴租金业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:49
 */
public interface ToPaidService extends IService<ToPaid> {
    void insertToPaid(ToPaid topaid) throws ServiceException;

    List<ToPaid> findToPaid(QueryVo vo);

    ToPaid findById(Long id);

    void goToPay(Long id, Paid paid);
}
