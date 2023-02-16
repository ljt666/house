package com.ljt.house.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.house.domain.HouseList;
import com.ljt.house.domain.QueryVo;
import com.ljt.house.service.ServiceInterface.HouseListService;
import com.ljt.house.util.RespCode;
import com.ljt.house.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.util.List;

/**
 * @description: 房源列表controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/18 20:02
 */
@Controller
@RequestMapping("/houselist")
public class HouseListController{

    private static final Logger logger = LoggerFactory.getLogger(HouseListController.class);

    @Autowired
    private HouseListService houselistService;

    /**
     * 图片二进制转base64格式
     * @responsebody这个注解表示你的返回值将存在responsebody中返回到前端
     */
    @ResponseBody
    public List<HouseList> transform(QueryVo vo){
        List<HouseList> houselist = houselistService.selectHouselist(vo);
        houselist.stream().forEach(house->{
            try {
                byte[] picture = house.getPicture();
                if(picture!=null&&picture.length>0) {
                    //将图片二进制转为BASE64格式发给前端，html5 src属性能自动解码，注意（src要拼接'data:image/jpeg;base64,'前缀，
                    //一开始通过看转化后base64字符串，在线转图片死活转不了，发现少了这个前缀，于是在img中src属性前面拼接它）
                    String encode = BASE64Encoder.class.newInstance().encode(picture);
                    house.setEncode(new StringBuffer(encode));
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
        return houselist;
    }

    /**
     * 租客查询房源列表
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/houseList")
    public String houseList(Model model,
                            QueryVo vo,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        //调用图片二进制转base64发给前端方法
        List<HouseList> houselist = transform(vo);
        PageInfo<HouseList> p = new PageInfo<>(houselist);
        model.addAttribute("p", p);
        model.addAttribute("houselist", houselist);
        model.addAttribute("mainPage", "zuke/houselist.html");
        model.addAttribute("vo",vo);
        return "zuke/main";
    }

    /**
     * 管理员查询房源列表
     *
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/ahouseList")
    public String ahouseList(Model model,
                             QueryVo vo,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        //调用图片二进制转base64发给前端方法
        List<HouseList> houselist = transform(vo);
        PageInfo<HouseList> p = new PageInfo<>(houselist);
        model.addAttribute("p", p);
        model.addAttribute("houselist", houselist);
        model.addAttribute("mainPage", "admin/ahouselist.html");
        //一定要记得把该查询对象给前端传过去(这样前端才能用${vo})，在这里踩了3个多小时的坑
        model.addAttribute("vo", vo);
        //admin/main1
        return "admin/main1";
    }

    /**
     * 管理员添加房源信息
     *
     * @param model
     * @param houselist
     * @return
     */
    @RequestMapping("/addHouse")
    public String addHouse(Model model,
                           HouseList houselist) {
        HouseList houselist1 = houselistService.findByHouseCode(houselist.getHouseCode());
        if (houselist1 != null) {
            model.addAttribute("error", RespCode.HOUSE_CODE_EXIST);
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "admin/addhouse.html");
            return "admin/main1";
        } else {
            try {
                InputStream inputStream = houselist.getFile().getInputStream();
                byte[] picture;
                //读取多个字节
                picture = new byte[inputStream.available()];
                inputStream.read(picture);
                inputStream.close();
                houselist.setPicture(picture);
                houselistService.insertHouse(houselist);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            model.addAttribute("error", RespCode.ADD_SUCCESS);
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "admin/addhouse.html");
            return "admin/main1";
        }
    }

    /**
     * 跳转到添加房源信息的页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toAddHouse")
    public String toAddHouse(Model model) {
        model.addAttribute("mainPage", "admin/addhouse.html");
        return "admin/main1";
    }

    /**
     * 管理员删除房源信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteHouse")
    public String deleteHouse(@RequestParam Long id) {
        try {
            houselistService.deleteHouse(id);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/houselist/ahouseList";
    }

    /**
     * 管理员根据id查询房源信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findById")
    public String findById(@RequestParam Long id, Model model) {
        HouseList list = houselistService.findById(id);
        model.addAttribute("houselist", list);
        model.addAttribute("mainPage", "admin/changehouse.html");
        return "admin/main1";
    }

    /**
     * 管理员去更新房源信息
     *
     * @param houselist
     * @param model
     * @return
     */
    @RequestMapping("/findHouseCodeUpdate")
    public String findHouseCodeUpdate(HouseList houselist, Model model) {
        HouseList list = houselistService.findByHouseCodeUpdate(houselist);
        if (list == null) {
            model.addAttribute("houselist", houselist);
            model.addAttribute("error", RespCode.HOUSE_IS_NOT_EXIST);
            model.addAttribute("mainPage", "admin/changehouse.html");
            return "admin/main1";
        } else {
            try {
                houselistService.updateHouse(houselist);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
            model.addAttribute("houselist", houselist);
            model.addAttribute("error", RespCode.UPDATE_SUCCESS);
            model.addAttribute("mainPage", "admin/changehouse.html");
            //redirect:/houselist/ahouseList
            return "admin/main1";
        }
    }

    /**
     * 更新房源点击返回跳转到房源列表
     *
     * @return
     */
    @RequestMapping("/toHouselist")
    public String toHouselist() {
        return "redirect:/houselist/ahouseList";
    }

    /**
     * 添加房源后点击返回跳转到房源列表
     *
     * @return
     */
    @RequestMapping("/toHouselist2")
    public String toHouselist2() {
        return "redirect:/houselist/ahouseList";
    }
}
