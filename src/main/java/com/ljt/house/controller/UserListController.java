package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.User;
import com.ljt.house.domain.UserList;
import com.ljt.house.dto.UserListDto;
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
import java.util.List;

/**
 * @description: 租客controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/6 15:10
 */
@Controller
@RequestMapping("userlist")
public class UserListController {

    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    @Autowired
    private UserListService userlistService;

    /**
     * 租客查询自己的用户信息
     *
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/findHouseList")
    public String findHouseList(HttpSession httpSession, Model model) {
        User user1 = (User) httpSession.getAttribute("user");
        Long userId = user1.getId();
        UserList userlist=new UserList();
        UserList userlist2 = userlistService.findHouseList(userId);
        if(userlist2==null){
            model.addAttribute("userlist", userlist);
        }else{
            model.addAttribute("userlist", userlist2);
        }
        model.addAttribute("mainPage", "zuke/updateuserlist.html");
        return "zuke/main";
    }

    /**
     * 租客新增或更新用户信息（点击完善个人资料提交按钮触发）
     *
     * @param model
     * @param userlist
     * @param httpSession
     * @return
     */
    @RequestMapping("/checkUserList")
    public String checkUserList(Model model,
                                UserList userlist,
                                HttpSession httpSession) {
        //租客还没进行绑定
        if (userlist.getId() == null) {
            String idCard = userlist.getIdCard();
            UserList list = userlistService.checkUserList(idCard);
            //能查到身份证号
            if (list != null) {
                model.addAttribute("error", RespCode.ID_CARD_IS_BOUND);
                model.addAttribute("mainPage", "zuke/updateuserlist.html");
                model.addAttribute("userlist", userlist);
            }
            //不能查到身份证号
            else {
                //给当前登录用户新增租客信息
                User user1 = (User) httpSession.getAttribute("user");
                Long userId = user1.getId();
                userlist.setUserId(userId);
                try {
                    userlistService.insertUserList(userlist);
                } catch (ServiceException e) {
                    logger.error(e.getMessage());
                }
                UserList list1 = userlistService.checkUserList(idCard);
                model.addAttribute("error", RespCode.SUCCESSFUL_COMPLETION_OF_DATA);
                model.addAttribute("mainPage", "zuke/updateuserlist.html");
                model.addAttribute("userlist", list1);
            }
        }
        //绑定好了进行更新
        else {
            UserList list = userlistService.findUserListUpdate(userlist);
            if (list == null) {
                model.addAttribute("error", RespCode.NO_ZUKE_INFORMATION);
                model.addAttribute("mainPage", "zuke/updateuserlist.html");
                model.addAttribute("userlist", userlist);
            } else {
                try {
                    userlistService.updateUserList(userlist);
                } catch (ServiceException e) {
                    logger.error(e.getMessage());
                }
                model.addAttribute("error", RespCode.UPDATE_SUCCESS);
                model.addAttribute("mainPage", "zuke/updateuserlist.html");
                model.addAttribute("userlist", userlist);
            }
        }
        return "zuke/main";
    }

    /**
     * 管理员查询所有的租客信息
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAllUserList")
    public String findAllUserList(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<UserListDto> userlist = userlistService.findAllUserList();
        PageInfo<UserListDto> p = new PageInfo<>(userlist);
        model.addAttribute("userlist", userlist);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "admin/userlist.html");
        return "admin/main1";
    }

    /**
     * 管理员删除租客信息
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/deleteUserList")
    public String deleteUserList(Model model,
                                 @RequestParam Long id) {
        try {
            userlistService.deleteUserList(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "deletesuccess");
        return "redirect:/userlist/findAllUserList";
    }
}
