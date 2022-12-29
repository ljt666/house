package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.util.ServiceException;
import com.ljt.house.util.UtilEnum;
import com.ljt.house.domain.ApplyOut;
import com.ljt.house.domain.User;
import com.ljt.house.domain.UserList;
import com.ljt.house.domain.ZuList;
import com.ljt.house.dto.ApplyOutDto;
import com.ljt.house.dto.UserListDto;
import com.ljt.house.service.ServiceInterface.ApplyOutService;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.service.ServiceInterface.ZuListService;
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
 * @description: 退租申请controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/8 21:06
 */
@Controller
@RequestMapping("/applyout")
public class ApplyOutController {

    private static final Logger logger = LoggerFactory.getLogger(ApplyOutController.class);

    @Autowired
    private ZuListService zulistService;
    @Autowired
    private ApplyOutService applyoutService;
    @Autowired
    private UserListService userlistService;


    /**
     * 租客申请退租，插入退租信息
     *
     * @param houseCode
     * @param model
     * @return
     */
    @RequestMapping("/insertApplyOut")
    public String insertApplyOut(@RequestParam(value = "houseCode") String houseCode,
                                 Model model) {
        ZuList zulist = zulistService.findZuList(houseCode);
        try {
            applyoutService.insertApplyOut(zulist);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "applysuccess");
        return "redirect:/zulist/myZuList";
    }

    /**
     * 管理员查看退租申请
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAllApplyOut")
    public String findAllApplyOut(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ApplyOutDto> applyout = applyoutService.findAllApplyOut();
        PageInfo<ApplyOutDto> p = new PageInfo<>(applyout);
        model.addAttribute("applyout", applyout);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/applyout.html");
        return "admin/main1";
    }

    /**
     * 管理员拒绝退租申请
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/refuseApplyOut")
    public String refuseApplyOut(Model model,
                                 @RequestParam(value = "id") Long id) {
        ApplyOut applyout = ApplyOut.builder().id(id).status(UtilEnum.ApplyOutStatus2.getDescription()).build();
        try {
            applyoutService.updateApplyOut(applyout);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("mainPage", "admin/applyout.html");
        return "redirect:/applyout/findAllApplyOut";
    }

    /**
     * 管理员同意退租申请
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/agreeApplyOut")
    public String agreeApplyOut(Model model,
                                @RequestParam(value = "id") Long id) {
        try {
            applyoutService.agreeApplyOut(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "applyoutsuccess");
        return "redirect:/applyout/findAllApplyOut";
    }

    /**
     * 管理员删除申请退租列表
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/deleteApplyOut")
    public String deleteApplyOut(Model model,
                                 @RequestParam(value = "id") Long id) {
        try {
            applyoutService.deleteApplyOut(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "deletesuccess");
        return "redirect:/applyout/findAllApplyOut";
    }

    /**
     * 租客删除申请退租列表
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/deleteApplyOut2")
    public String deleteApplyOut2(Model model,
                                  @RequestParam(value = "id") Long id) {
        try {
            applyoutService.deleteApplyOut(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "deletesuccess");
        return "redirect:/applyout/getMyApplyOut";
    }

    /**
     * 租客查看自己的退房申请
     *
     * @param model
     * @param httpSession
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/getMyApplyOut")
    public String getMyApplyOut(Model model,
                                HttpSession httpSession,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserListDto> list = userlistService.getMyApplyOut(userlist.getId());
        PageInfo<UserListDto> p = new PageInfo<>(list);
        model.addAttribute("userlist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "zuke/myapplyout.html");
        return "zuke/main";
    }
}
