package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.Solve;
import com.ljt.house.domain.Wrong;
import com.ljt.house.persistence.SolveMapper;
import com.ljt.house.persistence.WrongMapper;
import com.ljt.house.service.ServiceInterface.SolveService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 已处理报障业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/18 14:24
 */
@Service
public class SolveServiceImpl extends ServiceImpl<SolveMapper, Solve> implements SolveService {
    @Autowired
    private SolveMapper solveMapper;
    @Autowired
    private WrongMapper wrongMapper;

    @Override
    public List<Solve> selectAll(QueryVo vo) {
        List<Solve> list = solveMapper.selectAll(vo);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public Integer selectCount(QueryVo vo) {
        Integer count = solveMapper.selectCount(vo);
        if (count == null) {
            return null;
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteSolve(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        }
        solveMapper.deleteById(id);
    }

    @Override
    public List<Wrong> findWrong(QueryVo vo) {
        List<Wrong> list = wrongMapper.findWrong(vo);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public Wrong findById(Long id) {
        Wrong wrong = wrongMapper.selectById(id);
        if (StringUtils.isEmpty(wrong)) {
            return null;
        }
        return wrong;
    }

    /**
     * @param wrong
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertWrong(Wrong wrong) throws ServiceException {
        if (StringUtils.isEmpty(wrong)) {
            throw new ServiceException(RespCode.WRONG_IS_NOT_NULL);
        } else {
            wrong.setStatus(RespCode.PENDING_DISPOSAL);
            wrongMapper.insert(wrong);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void goToSolve(Long id, Solve solve) {
        if (!StringUtils.isEmpty(solve) && id != null) {
            wrongMapper.deleteById(id);
            solveMapper.insert(solve);
        }
    }
}
