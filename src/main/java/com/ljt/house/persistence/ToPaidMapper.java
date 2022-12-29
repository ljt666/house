package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.domain.ToPaid;

import java.util.List;

/**
 * @description: 待缴租金dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:50
 */
public interface ToPaidMapper extends BaseMapper<ToPaid> {
    List<ToPaid> findToPaid(QueryVo vo);
}
