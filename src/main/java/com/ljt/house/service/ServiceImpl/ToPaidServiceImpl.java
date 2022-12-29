package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljt.house.domain.Paid;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.ToPaid;
import com.ljt.house.persistence.PaidMapper;
import com.ljt.house.persistence.ToPaidMapper;
import com.ljt.house.service.ServiceInterface.ToPaidService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 待缴租金业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:49
 */
@Service
@Transactional
public class ToPaidServiceImpl extends ServiceImpl<ToPaidMapper, ToPaid> implements ToPaidService {

    @Autowired
    private ToPaidMapper topaidMapper;
    @Autowired
    private PaidMapper paidMapper;

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertToPaid(ToPaid topaid) throws ServiceException {
        if (StringUtils.isEmpty(topaid)) {
            throw new ServiceException(RespCode.TOPAID_IS_NOT_NULL);
        } else {
            topaid.setStatus(RespCode.UNPAID_RENT);
            topaidMapper.insert(topaid);
        }
    }

    @Override
    public List<ToPaid> findToPaid(QueryVo vo) {
        List<ToPaid> list = topaidMapper.findToPaid(vo);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public ToPaid findById(Long id) {
        ToPaid topaid = topaidMapper.selectById(id);
        if (StringUtils.isEmpty(topaid)) {
            return null;
        }
        return topaid;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void goToPay(Long id, Paid paid) {
        if (id != null && !StringUtils.isEmpty(paid)) {
            paidMapper.insert(paid);
            topaidMapper.deleteById(id);
        }
    }
}
