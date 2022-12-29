package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.Solve;
import com.ljt.house.domain.Wrong;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 已处理报障业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/18 14:23
 */
public interface SolveService extends IService<Solve> {
    List<Solve> selectAll(QueryVo vo);

    Integer selectCount(QueryVo vo);

    void deleteSolve(Long id) throws ServiceException;

    List<Wrong> findWrong(QueryVo vo);

    Wrong findById(Long id);

    void insertWrong(Wrong wrong) throws ServiceException;

    void goToSolve(Long id, Solve solve);
}
