package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.*;
import com.ljt.house.dto.ApplyDto;
import com.ljt.house.dto.UserListDto;
import com.ljt.house.service.ServiceInterface.ApplyService;
import com.ljt.house.service.ServiceInterface.HouseListService;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import com.ljt.house.util.UtilEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 看房申请controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/23 09:44
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {

    private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private UserListService userlistService;
    @Autowired
    private HouseListService houselistService;
    @Autowired
    private ApplyService applyService;

    /**
     * 租客申请看房
     *
     * @param httpSession
     * @param id
     * @return
     */
    @RequestMapping("/applyCheckUserList")
    public String applyCheckUserList(HttpSession httpSession,
                                     @RequestParam(value = "id") Long id) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList list = userlistService.findHouseList(user1.getId());
        HouseList houselist = houselistService.findById(id);
        houselist.setStatus(UtilEnum.HouseListStatus3.getDescription());
        try {
            houselistService.updateHouseStatus(houselist);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        Apply apply = Apply.builder()
                .houseCode(houselist.getHouseCode())
                .address(houselist.getAddress())
                .price(houselist.getPrice())
                .area(houselist.getArea())
                .status(RespCode.Apply_Status_Application)
                .userlistId(list.getId())
                .build();
        try {
            applyService.insertApply(apply);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/houselist/houseList";
    }

    /**
     * 管理员查看申请看房列表
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findApplyList")
    public String findApplylist(Model model,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ApplyDto> applylist = applyService.findApplylist();
        PageInfo<ApplyDto> p = new PageInfo<>(applylist);
        model.addAttribute("applylist", applylist);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/applylist.html");
        return "admin/main1";
    }

    /**
     * 管理员拒绝看房申请
     *
     * @param houseCode
     * @return
     */
    @RequestMapping("/refuseApply")
    public String refuseApply(@RequestParam(value = "houseCode") String houseCode) {
        try {
            applyService.refuseApply(houseCode);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/apply/findApplyList";
    }

    /**
     * 租客查看自己的看房申请
     *
     * @param model
     * @param httpSession
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/getMyApply")
    public String getMyApply(Model model,
                             HttpSession httpSession,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserListDto> list = userlistService.getMyApply(userlist.getId());
        PageInfo<UserListDto> p = new PageInfo<>(list);
        model.addAttribute("userlist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "zuke/myapply.html");
        return "zuke/main";
    }
}
