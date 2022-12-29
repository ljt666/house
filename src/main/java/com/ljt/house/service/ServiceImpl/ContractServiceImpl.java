package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljt.house.domain.Contract;
import com.ljt.house.persistence.ContractMapper;
import com.ljt.house.service.ServiceInterface.ContractService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @description: 合同业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/28 13:25
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {

    @Autowired
    private ContractMapper contractMapper;

    /**
     * 获取基本不带条件的EntityWrapper对象
     */
    public EntityWrapper<Contract> getInitWrapper() {
        EntityWrapper<Contract> initWrapper = new EntityWrapper<>();
        return initWrapper;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void insertContract(Contract contract) throws ServiceException {
        if (StringUtils.isEmpty(contract)) {
            throw new ServiceException(RespCode.CONTRACT_IS_NOT_NULL);
        }
        contractMapper.insert(contract);
    }

    @Override
    public Contract findContract(String houseCode) {
        Contract contract = this.selectOne(getInitWrapper().eq("house_code", houseCode));
        if (!StringUtils.isEmpty(contract)) {
            return contract;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateContract(Contract contract) {
        Wrapper<Contract> wrapper = getInitWrapper().eq(contract.getHouseCode() != null, "house_code", contract.getHouseCode());
        contractMapper.update(contract, wrapper);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteContract(String houseCode) {
        contractMapper.delete(getInitWrapper().eq(houseCode != null, "house_code", houseCode));
    }

    @Override
    public Contract findContractById(Long id) {
        Contract contract = contractMapper.selectById(id);
        if (!StringUtils.isEmpty(contract)) {
            return contract;
        }
        return null;
    }
}
