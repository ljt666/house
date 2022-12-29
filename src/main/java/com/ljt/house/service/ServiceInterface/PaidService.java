package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.Paid;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.util.ServiceException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 已缴租金业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:47
 */
public interface PaidService extends IService<Paid> {
    List<Paid> selectAll(QueryVo vo);

    BigDecimal selectSum(QueryVo vo);

    void deletePaid(Long id) throws ServiceException;

    List<ZulistDto> findZuUserList();

    ZulistDto findZukeZuList(Long id);
}
