package com.ljt.house.service.ServiceInterface;

import com.baomidou.mybatisplus.service.IService;
import com.ljt.house.domain.Schedule;
import com.ljt.house.util.ServiceException;

import java.util.List;

/**
 * @description: 日程业务逻辑层
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/4/2 10:36
 */
public interface ScheduleService extends IService<Schedule> {
    void insertSchedule(Schedule schedule) throws ServiceException;

    List<Schedule> selectAll();

    void deleteSchedule(Long id) throws ServiceException;

    void updateSchedule(Schedule schedule) throws ServiceException;

    Schedule selectById(Long id);
}
