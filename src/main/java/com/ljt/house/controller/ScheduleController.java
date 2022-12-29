package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.Schedule;
import com.ljt.house.service.ServiceInterface.ScheduleService;
import com.ljt.house.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description: 日程controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/4/2 10:35
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 管理员查询日程
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAll")
    public String selectAll(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Schedule> schedule = scheduleService.selectAll();
        PageInfo<Schedule> p = new PageInfo<>(schedule);
        model.addAttribute("schedule", schedule);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/schedule.html");
        return "admin/main1";
    }

    /**
     * 管理员删除日程
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteSchedule")
    public String deleteSchedule(@RequestParam Long id) {
        try {
            scheduleService.deleteSchedule(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/schedule/selectAll";
    }

    /**
     * 管理员插入日程
     *
     * @param schedule
     * @return
     */
    @RequestMapping("/insertSchedule")
    public String insertSchedule(Schedule schedule) {
        try {
            scheduleService.insertSchedule(schedule);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/schedule/selectAll";

    }

    /**
     * 管理员更新日程
     *
     * @param schedule
     * @param model
     * @return
     */
    @RequestMapping("/updateSchedule")
    public String updateSchedule(Schedule schedule, Model model) {
        try {
            scheduleService.updateSchedule(schedule);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("schedule", schedule);
        model.addAttribute("mainPage", "admin/updateschedule.html");
        return "redirect:/schedule/selectAll";
    }

    /**
     * 跳转到增加日程页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toInsert")
    public String toInsert(Model model, Schedule schedule) {
        model.addAttribute("schedule",schedule);
        model.addAttribute("mainPage", "admin/addschedule.html");
        return "admin/main1";
    }

    /**
     * 跳转到更新日程页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Model model, @RequestParam Long id) {
        Schedule schedule = scheduleService.selectById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("mainPage", "admin/updateschedule.html");
        return "admin/main1";
    }

    /**
     * 添加房源后点击返回跳转到房源列表
     *
     * @return
     */
    @RequestMapping("/toSchedule")
    public String toSchedule() {
        return "redirect:/schedule/selectAll";
    }
}
