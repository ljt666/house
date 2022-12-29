package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.Checkout;
import com.ljt.house.dto.CheckoutDto;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 已退租列表业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/13 12:08
 */
public interface CheckoutService extends IService<Checkout> {
    void insertCheckout(Checkout checkout) throws ServiceException;

    List<CheckoutDto> getAllCheckout();

    void deleteCheckout(Long id) throws ServiceException;
}
