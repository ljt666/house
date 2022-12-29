package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljt.house.domain.Checkout;
import com.ljt.house.dto.CheckoutDto;
import com.ljt.house.persistence.CheckoutMapper;
import com.ljt.house.service.ServiceInterface.CheckoutService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 已退租列表业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/13 12:08
 */
@Service
public class CheckoutServiceImpl extends ServiceImpl<CheckoutMapper, Checkout> implements CheckoutService {

    @Autowired
    private CheckoutMapper checkoutMapper;

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertCheckout(Checkout checkout) throws ServiceException {
        if (StringUtils.isEmpty(checkout)) {
            throw new ServiceException(RespCode.RENT_REFUNDED_LIST_CANNOT_BE_EMPTY);
        }
        checkoutMapper.insert(checkout);
    }

    @Override
    public List<CheckoutDto> getAllCheckout() {
        List<CheckoutDto> checkoutList = checkoutMapper.getAllCheckout();
        if (CollectionUtils.isEmpty(checkoutList)) {
            return new ArrayList<>();
        }
        return checkoutList;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteCheckout(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        }
        checkoutMapper.deleteById(id);
    }
}
