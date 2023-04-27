package com.ljt.house.service.ServiceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljt.house.domain.Schedule;
import com.ljt.house.persistence.ScheduleMapper;
import com.ljt.house.service.ServiceInterface.ScheduleService;
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
 * @description: 日程业务逻辑实现类
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/4/2 10:36
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;




    

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    //声明式事务管理
    public void insertSchedule(Schedule schedule) throws ServiceException {
        if (StringUtils.isEmpty(schedule)) {
            throw new ServiceException(RespCode.SCHEDULE_IS_NOT_NULL);
        }
        scheduleMapper.insert(schedule);
    }

    @Override
    public List<Schedule> selectAll() {
        List<Schedule> list = scheduleMapper.selectList(null);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteSchedule(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RespCode.ID_IS_NOT_NULL);
        }
        scheduleMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateSchedule(Schedule schedule) throws ServiceException {
        if (StringUtils.isEmpty(schedule)) {
            throw new ServiceException(RespCode.SCHEDULE_IS_NOT_NULL);
        }
        scheduleMapper.updateById(schedule);
    }

    @Override
    public Schedule selectById(Long id) {
        Schedule schedule = scheduleMapper.selectById(id);
        if (StringUtils.isEmpty(schedule)) {
            return null;
        }
        return schedule;
    }
}
