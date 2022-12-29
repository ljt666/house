package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.Wrong;

import java.util.List;

/**
 * @description: 报障dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/2 12:40
 */
public interface WrongMapper extends BaseMapper<Wrong> {
    List<Wrong> findWrong(QueryVo vo);
}
