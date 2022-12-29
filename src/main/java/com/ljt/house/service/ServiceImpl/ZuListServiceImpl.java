package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ljt.house.domain.ZuList;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.persistence.ZuListMapper;
import com.ljt.house.service.ServiceInterface.ZuListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 在租列表业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/31 10:34
 */
@Service
public class ZuListServiceImpl extends ServiceImpl<ZuListMapper, ZuList> implements ZuListService {

    @Autowired
    private ZuListMapper zulistMapper;

    /**
     * 获取基本不带条件的EntityWrapper对象
     */
    public EntityWrapper<ZuList> getInitWrapper() {
        EntityWrapper<ZuList> initWrapper = new EntityWrapper<>();
        return initWrapper;
    }

    @Override
    public void insertZuList(ZuList zulist) {
        zulistMapper.insert(zulist);
    }

    @Override
    public List<ZulistDto> findZuUserList() {
        List<ZulistDto> zulist = zulistMapper.findZuUserList();
        if (CollectionUtils.isNotEmpty(zulist)) {
            return zulist;
        }
        return new ArrayList<>();
    }

    @Override
    public ZuList findZuList(String houseCode) {
        ZuList zulist = this.selectOne(getInitWrapper().eq("house_code", houseCode));
        if (StringUtils.isEmpty(zulist)) {
            return null;
        }
        return zulist;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteZuList(String houseCode) throws ServiceException {
        if (houseCode == null) {
            throw new ServiceException(RespCode.HOUSECODE_IS_NOT_NULL);
        }
        zulistMapper.delete(getInitWrapper().eq("house_code", houseCode));
    }

    @Override
    public List<ZulistDto> findZuListByUid(Long userlistId) {
        List<ZulistDto> zulist = zulistMapper.findZuListByUid(userlistId);
        if (CollectionUtils.isNotEmpty(zulist)) {
            return zulist;
        }
        return new ArrayList<>();
    }

    @Override
    public ZuList findZulistById(Long id) {
        ZuList zuList = zulistMapper.selectById(id);
        if (!StringUtils.isEmpty(zuList)) {
            return zuList;
        }
        return null;
    }
}
