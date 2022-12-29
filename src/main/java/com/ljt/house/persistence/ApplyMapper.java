package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.Apply;
import com.ljt.house.dto.ApplyDto;

import java.util.List;

/**
 * @description: 看房申请dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/23 09:46
 */
public interface ApplyMapper extends BaseMapper<Apply> {
    List<ApplyDto> findApplylist();
}
