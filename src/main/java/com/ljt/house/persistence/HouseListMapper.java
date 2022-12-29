package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.HouseList;
import com.ljt.house.domain.QueryVo;

import java.util.List;

/**
 * @description: 房源列表dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/18 20:07
 */
public interface HouseListMapper extends BaseMapper<HouseList> {
    List<HouseList> selectHouselist(QueryVo vo);

    Integer findHouseListByVoCount(QueryVo vo);

    HouseList findByHouseCodeUpdate(HouseList houselist);
}
