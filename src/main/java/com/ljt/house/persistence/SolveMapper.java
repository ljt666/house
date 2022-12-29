package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.Solve;

import java.util.List;

/**
 * @description: 已处理报障dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/18 14:24
 */
public interface SolveMapper extends BaseMapper<Solve> {
    List<Solve> selectAll(QueryVo vo);

    Integer selectCount(QueryVo vo);
}
