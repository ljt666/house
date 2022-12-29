package com.ljt.house.persistence;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ljt.house.domain.Checkout;
import com.ljt.house.dto.CheckoutDto;

import java.util.List;

/**
 * @description: 已退租dao层接口
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/13 12:09
 */
public interface CheckoutMapper extends BaseMapper<Checkout> {
	List<CheckoutDto> getAllCheckout();
}
