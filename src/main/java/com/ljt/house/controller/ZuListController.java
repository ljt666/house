package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.Contract;
import com.ljt.house.domain.User;
import com.ljt.house.domain.UserList;
import com.ljt.house.dto.UserListDto;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.service.ServiceInterface.ZuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 在租列表controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/31 10:32
 */
@Controller
@RequestMapping("/zulist")
public class ZuListController {
    @Autowired
    private ZuListService zulistService;
    @Autowired
    private UserListService userlistService;

    /**
     * 管理员跳到增添合同的页面
     *
     * @param model
     * @param houseCode
     * @return
     */
    @RequestMapping("/toAddContract")
    public String toAddContract(Model model,
                                @RequestParam String houseCode,
                                @RequestParam String address,
                                @RequestParam String name,
                                @RequestParam String idCard,
                                @RequestParam BigDecimal price) {
        Contract contract = Contract.builder()
                .houseCode(houseCode)
                .address(address)
                .zuke(name)
                .zukeIdCard(idCard)
                .price(price).build();
        model.addAttribute("contract", contract);
        model.addAttribute("mainPage", "admin/addcontract.html");
        return "admin/main1";
    }

    /**
     * 管理员查看所有在租列表
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findZuList")
    public String findZuList(Model model,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ZulistDto> zulistDto = zulistService.findZuUserList();
        PageInfo<ZulistDto> p = new PageInfo<>(zulistDto);
        model.addAttribute("p", p);
        model.addAttribute("zulist", zulistDto);
        model.addAttribute("mainPage", "admin/zulist.html");
        return "admin/main1";
    }

    /**
     * 租客查看我的租赁
     *
     * @param model
     * @param httpSession
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/myZuList")
    public String myZuList(Model model,
                           HttpSession httpSession,
                           @RequestParam(required = false, defaultValue = "1") Integer page,
                           @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user.getId());
        PageHelper.startPage(page, pageSize);
        List<UserListDto> list = userlistService.getUserZuList(userlist.getId());
        PageInfo<UserListDto> p = new PageInfo<>(list);
        model.addAttribute("userlistzu", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "zuke/myzulist.html");
        return "zuke/main";
    }

}
