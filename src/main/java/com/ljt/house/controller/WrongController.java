package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.*;
import com.ljt.house.dto.ZulistDto;
import com.ljt.house.service.ServiceInterface.PaidService;
import com.ljt.house.service.ServiceInterface.SolveService;
import com.ljt.house.service.ServiceInterface.UserListService;
import com.ljt.house.service.ServiceInterface.ZuListService;
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
import java.util.List;

/**
 * @description: 报障controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/3/2 12:32
 */
@Controller
@RequestMapping("/wrong")
public class WrongController {

    private static final Logger logger = LoggerFactory.getLogger(WrongController.class);

    @Autowired
    private SolveService solveService;
    @Autowired
    private UserListService userlistService;
    @Autowired
    private PaidService paidService;
    @Autowired
    private ZuListService zulistService;

    /**
     * 管理员查找所有已处理的报障
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
        List<Solve> list = solveService.selectAll(vo);
        PageInfo<Solve> p = new PageInfo<>(list);
        Integer count = solveService.selectCount(vo);
        model.addAttribute("solve", list);
        model.addAttribute("count", count);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/solve.html");
        model.addAttribute("vo", vo);
        return "admin/main1";
    }

    /**
     * 租客查找自己已处理的报障
     *
     * @param httpSession
     * @param model
     * @param vo
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findMySolve")
    public String findMySolve(HttpSession httpSession,
                              Model model,
                              QueryVo vo,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        vo.setUserlistId(userlist.getId());
        PageHelper.startPage(page, pageSize);
        List<Solve> list = solveService.selectAll(vo);
        PageInfo<Solve> p = new PageInfo<>(list);
        Integer count = solveService.selectCount(vo);
        model.addAttribute("solve", list);
        model.addAttribute("count", count);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "zuke/mysolve.html");
        model.addAttribute("vo", vo);
        return "zuke/main";
    }

    /**
     * 管理员删除已处理报障记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteSolve")
    public String deleteSolve(@RequestParam Long id) {
        try {
            solveService.deleteSolve(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/wrong/selectAll";
    }

    /**
     * 租客删除自己的已处理报障记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteSolveByZuke")
    public String deleteSolveByZuke(@RequestParam Long id) {
        try {
            solveService.deleteSolve(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/wrong/findMySolve";
    }

    /**
     * 租客跳到我要报障页面
     *
     * @param httpSession
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/showAddWrong")
    public String showAddWrong(HttpSession httpSession,
                               Model model,
                               @RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<ZulistDto> list = zulistService.findZuListByUid(userlist.getId());
        PageInfo<ZulistDto> p = new PageInfo<>(list);
        model.addAttribute("zulist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "zuke/showaddwrong.html");
        return "zuke/main";
    }

    /**
     * 租客点击报障后跳转到添加报障信息页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/addWrong")
    public String addWrong(@RequestParam Long id, Model model) {
        ZulistDto zulistDto = paidService.findZukeZuList(id);
        model.addAttribute("zulist", zulistDto);
        model.addAttribute("mainPage", "zuke/addwrong.html");
        return "zuke/main";
    }

    /**
     * 租客添加报障信息到wrong表
     *
     * @param wrong
     * @return
     */
    @RequestMapping("/insertWrong")
    public String insertWrong(Wrong wrong) {
        try {
            solveService.insertWrong(wrong);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/wrong/showAddWrong";
    }

    /**
     * 管理员查看所有未处理报障
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/wrongList")
    public String wrongList(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        QueryVo vo = new QueryVo();
        PageHelper.startPage(page, pageSize);
        List<Wrong> list = solveService.findWrong(vo);
        PageInfo<Wrong> p = new PageInfo<>(list);
        model.addAttribute("wrong", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/wrong.html");
        return "admin/main1";
    }

    /**
     * 租客查看自己的未处理报障
     *
     * @param model
     * @param httpSession
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/myWrongList")
    public String myWrongList(Model model, HttpSession httpSession,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userlist = userlistService.findHouseList(user1.getId());
        QueryVo vo = new QueryVo();
        vo.setUserlistId(userlist.getId());
        PageHelper.startPage(page, pageSize);
        List<Wrong> list = solveService.findWrong(vo);
        PageInfo<Wrong> p = new PageInfo<>(list);
        model.addAttribute("p", p);
        model.addAttribute("wrong", list);
        model.addAttribute("mainPage", "zuke/mywrong.html");
        return "zuke/main";
    }

    /**
     * 管理员处理报障
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/goToSolve")
    public String goToSolve(@RequestParam Long id, Model model) {
        Wrong wrong = solveService.findById(id);
        Solve solve = Solve.builder()
                .houseCode(wrong.getHouseCode())
                .address(wrong.getAddress())
                .date(wrong.getDate())
                .detail(wrong.getDetail())
                .name(wrong.getName())
                .userlistId(wrong.getUserlistId())
                .status(RespCode.SOLVE).build();
        solveService.goToSolve(id, solve);
        model.addAttribute("error", "duesucess");
        return "redirect:/wrong/selectAll";
    }
}
