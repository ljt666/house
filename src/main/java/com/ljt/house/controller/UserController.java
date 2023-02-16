package com.ljt.house.controller;

import com.ljt.house.domain.User;
import com.ljt.house.service.ServiceInterface.UserService;
import com.ljt.house.util.EncryptUtil;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.UtilEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @description: 用户controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/5 10:00
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 处理登录逻辑
     *
     * @param username
     * @param password
     * @param type
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping("/loginCheck")
    public String loginCheck(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String type,
                             Model model,
                             HttpSession httpSession) {
        //登录进行MD5加密
        String password1 = EncryptUtil.MD5(password);
        User user = userService.login(username, password1, type);
        //查到了，可以登录
        //user != null也行
        if (!StringUtils.isEmpty(user)) {
            //把登录信息传给前端
            httpSession.setAttribute("user", user);
            if (user.getType().equals(UtilEnum.UserType2.getDescription())) {
                return "zuke/main";
            } else {
                return "admin/main1";
            }
        }
        //user为空，没查到的情况，包括用户名，密码，类型错误
        else {
            //根据用户名去查
            User userByUsername = userService.findUserByUsername(username);
            //用户名不存在的情况
            if (StringUtils.isEmpty(userByUsername)) {
                model.addAttribute("error", RespCode.USER_NAME_IS__NOT_EXIST);
            }
            //用户名存在的情况，进而判断密码和类型是否正确
            else {
                //密码不正确的情况
                if (!userByUsername.getPassword().equals(password1)) {
                    model.addAttribute("error", RespCode.PASSWORD_NOT_RIGHT);
                }
                //密码正确，类型不正确的情况
                else {
                    if (!userByUsername.getType().equals(type)) {
                        model.addAttribute("error", RespCode.TYPE_SELECT_ERROR);
                    }
                }
            }
            //没查到还是停留在当前页面   redirect:/user/login  也可以直接返回页面
            return "login";
        }
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 处理注册逻辑
     *
     * @param username
     * @param password
     * @param type
     * @param model
     * @return
     */
    @RequestMapping("/registerCheck")
    public String registerCheck(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String type,
                                Model model) {
        //首先根据用户名去找，找到提示该用户名已存在
        User user = userService.findUserByUsername(username);
        if (!StringUtils.isEmpty(user)) {
            model.addAttribute("error", RespCode.USER_NAME_IS_EXIST);
            return "register";
        }
        //数据库中没有
        else {
            //进行MD5加密
            String password1 = EncryptUtil.MD5(password);
            userService.register(username, password1, type);
            model.addAttribute("error", RespCode.REGISTER_SUCCESS);
            //注册成功跳转到登录页面
            return "login";
        }
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "admin/main1";
    }
}

