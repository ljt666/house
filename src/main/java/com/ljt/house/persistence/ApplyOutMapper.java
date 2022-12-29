package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.ApplyOut;
import com.ljt.house.dto.ApplyOutDto;

import java.util.List;

/**
 * @description: 退租申请dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/8 21:07
 */
public interface ApplyOutMapper extends BaseMapper<ApplyOut> {
	List<ApplyOutDto> findAllApplyOut();
}
