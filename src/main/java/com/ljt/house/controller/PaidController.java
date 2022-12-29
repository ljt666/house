
package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.*;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.service.ServiceInterface.PaidService;
import com.ljt.house.service.ServiceInterface.ToPaidService;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description: 已缴租金controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/2/16 17:46
 */
@Controller
@RequestMapping("/paid")
public class PaidController {

    private static final Logger logger = LoggerFactory.getLogger(PaidController.class);

    @Autowired
    private PaidService paidService;
    @Autowired
    private ToPaidService topaidService;
    @Autowired
    private UserListService userlistService;

    /**
     * 管理员查找所有已缴租金列表
     *
     * @param model
     * @param vo
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAll")
    public String selectAll(Model model,
                            QueryVo vo,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Paid> list = paidService.selectAll(vo);
        PageInfo<Paid> p = new PageInfo<>(list);
        BigDecimal sum = paidService.selectSum(vo);
        model.addAttribute("paid", list);
        model.addAttribute("sum", sum);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/paid.html");
        model.addAttribute("vo", vo);
        return "admin/main1";
    }

    /**
     * 租客查找自己已缴租金列表
     *
     * @param httpSession
     * @param model
     * @param vo
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findMyPaid")
    public String findMyPaid(HttpSession httpSession,
                             Model model,
                             QueryVo vo,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        vo.setUserlistId(userlist.getId());
        PageHelper.startPage(page, pageSize);
        List<Paid> list = paidService.selectAll(vo);
        PageInfo<Paid> p = new PageInfo<>(list);
        BigDecimal sum = paidService.selectSum(vo);
        model.addAttribute("paid", list);
        model.addAttribute("sum", sum);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "zuke/mypaid.html");
        model.addAttribute("vo", vo);
        return "zuke/main";
    }

    /**
     * 管理员删除已缴租金记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/deletePaid")
    public String deletePaid(@RequestParam Long id) {
        try {
            paidService.deletePaid(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/paid/selectAll";
    }

    /**
     * 租客删除已缴租金记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/deletePaidbyZuke")
    public String deletePaidbyZuke(@RequestParam Long id) {
        try {
            paidService.deletePaid(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/paid/findMyPaid";
    }

    /**
     * 管理员-跳转到我要收租页面
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/showAddPaid")
    public String showAddPaid(Model model,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ZulistDto> list = paidService.findZuUserList();
        PageInfo<ZulistDto> p = new PageInfo<>(list);
        model.addAttribute("zulist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/showaddpaid.html");
        //admin/showaddpaid
        return "admin/main1";
    }

    /**
     * 管理员点击收租后跳转到添加租金信息页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/addPaid")
    public String addPaid(@RequestParam Long id, Model model) {
        ZulistDto zulistDto = paidService.findZukeZuList(id);
        model.addAttribute("zulist", zulistDto);
        model.addAttribute("mainPage", "admin/addpaid.html");
        return "admin/main1";
    }

    /**
     * 管理员添加租金信息到topaid-待缴租金表
     *
     * @param topaid
     * @return
     */
    @RequestMapping("/insertToPaid")
    public String insertToPaid(ToPaid topaid) {
        try {
            topaidService.insertToPaid(topaid);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/paid/showAddPaid";
    }

    /**
     * 管理员查看所有未缴租金信息
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/toPaidList")
    public String toPaidList(Model model,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        QueryVo vo = new QueryVo();
        PageHelper.startPage(page, pageSize);
        List<ToPaid> list = topaidService.findToPaid(vo);
        PageInfo<ToPaid> p = new PageInfo<>(list);
        model.addAttribute("topaid", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/topaid.html");
        return "admin/main1";
    }

    /**
     * 租客查看自己的未缴租金
     *
     * @param model
     * @param httpSession
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/myToPaidList")
    public String myToPaidList(Model model, HttpSession httpSession,
                               @RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        QueryVo vo = new QueryVo();
        vo.setUserlistId(userlist.getId());
        PageHelper.startPage(page, pageSize);
        List<ToPaid> topaid = topaidService.findToPaid(vo);
        PageInfo<ToPaid> p = new PageInfo<>(topaid);
        model.addAttribute("p", p);
        model.addAttribute("topaid", topaid);
        model.addAttribute("mainPage", "zuke/mytopaid.html");
        return "zuke/main";
    }

    /**
     * 租客进行支付操作
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/goToPay")
    public String goToPay(@RequestParam Long id, Model model) {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String payDate = matter1.format(dt);
        ToPaid topaid = topaidService.findById(id);
        Paid paid = Paid.builder()
                .houseCode(topaid.getHouseCode())
                .address(topaid.getAddress())
                .price(topaid.getPrice())
                .date(topaid.getDate())
                .payDate(payDate)
                .name(topaid.getName())
                .userlistId(topaid.getUserlistId())
                .status(RespCode.RENT_PAID).build();
        topaidService.goToPay(id, paid);
        model.addAttribute("error", "paysucess");
        return "redirect:/paid/myToPaidList";
    }
}
