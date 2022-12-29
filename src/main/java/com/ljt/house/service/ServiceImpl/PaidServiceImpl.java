package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ljt.house.domain.Paid;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.persistence.PaidMapper;
import com.ljt.house.persistence.ZuListMapper;
import com.ljt.house.service.ServiceInterface.PaidService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 已缴租金业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:47
 */
@Service
public class PaidServiceImpl extends ServiceImpl<PaidMapper, Paid> implements PaidService {
    @Autowired
    private PaidMapper paidMapper;
    @Autowired
    private ZuListMapper zulistMapper;

    @Override
    public List<Paid> selectAll(QueryVo vo) {
        List<Paid> list = paidMapper.selectAll(vo);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public BigDecimal selectSum(QueryVo vo) {
        BigDecimal sum = paidMapper.selectSum(vo);
        if (sum == null) {
            return BigDecimal.ZERO;
        }
        return sum;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deletePaid(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        }
        paidMapper.deleteById(id);
    }

    @Override
    public List<ZulistDto> findZuUserList() {
        List<ZulistDto> list = zulistMapper.findZuUserList();
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public ZulistDto findZukeZuList(Long id) {
        ZulistDto zulistDto = zulistMapper.findZukeZuList(id);
        if (!StringUtils.isEmpty(zulistDto)) {
            return zulistDto;
        }
        return null;
    }
}
