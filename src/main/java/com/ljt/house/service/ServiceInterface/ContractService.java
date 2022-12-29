package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.Contract;
import com.ljt.house.util.ServiceException;

/**
 * @description: 合同业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/28 13:24
 */
public interface ContractService extends IService<Contract> {
    void insertContract(Contract contract) throws ServiceException;

    Contract findContract(String houseCode);

    void updateContract(Contract contract) throws ServiceException;

    void deleteContract(String houseCode) throws ServiceException;

    Contract findContractById(Long id);
}
