package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.ZuList;
import com.ljt.house.dto.ZulistDto;

import java.util.List;

/**
 * @description: 在租列表dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/31 10:34
 */
public interface ZuListMapper extends BaseMapper<ZuList> {
    List<ZulistDto> findZuUserList();

    List<ZulistDto> findZuListByUid(Long userlistId);

    ZulistDto findZukeZuList(Long id);
}
