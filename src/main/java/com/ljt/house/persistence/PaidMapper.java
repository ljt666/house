package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.Paid;
import com.ljt.house.domain.QueryVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 已缴租金dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:48
 */
public interface PaidMapper extends BaseMapper<Paid> {
    List<Paid> selectAll(QueryVo vo);

    BigDecimal selectSum(QueryVo vo);
}
