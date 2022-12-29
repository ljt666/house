package com.ljt.house.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.User;
import com.ljt.house.domain.UserList;
import com.ljt.house.dto.CheckoutDto;
import com.ljt.house.dto.UserListDto;
import com.ljt.house.service.ServiceInterface.CheckoutService;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.util.ServiceException;
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
 * @description: 已退租controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/13 12:07
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserListService userlistService;

    /**
     * 管理员查询所有的退租列表
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllCheckout")
    public String getAllCheckout(Model model,
                                 @RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CheckoutDto> checkout = checkoutService.getAllCheckout();
        PageInfo<CheckoutDto> p = new PageInfo<>(checkout);
        model.addAttribute("p", p);
        model.addAttribute("checkout", checkout);
        model.addAttribute("mainPage", "admin/checkout.html");
        return "admin/main1";
    }

    /**
     * 租客删除自己已退租列表
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteCheckout")
    public String deleteCheckout(@RequestParam Long id) {
        try {
            checkoutService.deleteCheckout(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/checkout/getMyCheckout";
    }

    /**
     * 管理员删除自己已退租列表
     *
     * @param id
     * @return
     */
    @RequestMapping("/adminDeleteCheckout")
    public String adminDeleteCheckout(@RequestParam Long id) {
        try {
            checkoutService.deleteCheckout(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/checkout/getAllCheckout";
    }

    /**
     * 租客查询自己的已退租列表
     *
     * @param model
     * @param httpSession
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/getMyCheckout")
    public String getMyCheckout(Model model,
                                HttpSession httpSession,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserListDto> list = userlistService.getMyCheckout(userlist.getId());
        PageInfo<UserListDto> p = new PageInfo<>(list);
        model.addAttribute("p", p);
        model.addAttribute("userlist", list);
        model.addAttribute("mainPage", "zuke/mycheckout.html");
        return "zuke/main";
    }
}
