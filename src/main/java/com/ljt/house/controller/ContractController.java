package com.ljt.house.controller;

import com.ljt.house.domain.*;
import com.ljt.house.service.ServiceInterface.*;
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

/**
 * @description: 合同controller
 * @version: 1.0
 * @author: jingtao.li@hand-china.com
 * @date: 2020/1/28 13:24
 */
@Controller
@RequestMapping("/contract")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;
    @Autowired
    private HouseListService houselistService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private ZuListService zulistService;
    @Autowired
    private CheckoutService checkoutService;

    /**
     * 管理员新增合同信息，修改房屋列表的状态，从申请列表中删除，增添到在租列表当中
     *
     * @param model
     * @param contract
     * @return
     */
    @RequestMapping("/insertContract")
    public String insertContract(Model model,
                                 Contract contract) {
        //新增合同信息
        try {
            contractService.insertContract(contract);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        Contract contract1 = contractService.findContract(contract.getHouseCode());
        //修改房屋列表状态为已租赁
        HouseList houselist = houselistService.findByHouseCode(contract1.getHouseCode());
        houselist.setStatus(UtilEnum.HouseListStatus2.getDescription());
        try {
            houselistService.updateHouseStatus(houselist);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        //添加到在租列表当中
        Apply apply = applyService.findByHouseCode(contract.getHouseCode());
        ZuList zulist = ZuList.builder()
                .houseCode(contract.getHouseCode())
                .userlistId(apply.getUserlistId())
                .contractId(contract1.getId())
                .price(apply.getPrice())
                .address(apply.getAddress()).build();
        zulistService.insertZuList(zulist);
        //从申请列表中删除
        try {
            applyService.deleteByHouseCode(contract1.getHouseCode());
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "zusuccess");
        return "redirect:/zulist/findZuList";
    }

    /**
     * 管理员查看合同信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/viewContractByAdmin")
    public String viewContractByAdmin(@RequestParam Long id, Model model) {
        ZuList zulistById = zulistService.findZulistById(id);
        Contract contract = contractService.findContractById(zulistById.getContractId());
        model.addAttribute("contract", contract);
        model.addAttribute("mainPage", "admin/contract.html");
        return "admin/main1";
    }

    /**
     * 去跳转更新合同页面
     *
     * @param houseCode
     * @param model
     * @return
     */
    @RequestMapping("/updateContract")
    public String updateContract(@RequestParam String houseCode, Model model) {
        Contract contract = contractService.findContract(houseCode);
        ZuList zuList = zulistService.findZuList(houseCode);
        model.addAttribute("contract", contract);
        model.addAttribute("zulist", zuList);
        model.addAttribute("mainPage", "admin/updatecontract.html");
        return "admin/main1";
    }

    /**
     * 管理员更新合同信息
     *
     * @param model,contract,id(zulist的id)
     * @return
     */
    @RequestMapping("/changeContract")
    public String changeContract(Model model,
                                 Contract contract,
                                 @RequestParam Long id) {
        Contract contract1 = contractService.findContract(contract.getHouseCode());
        ZuList zuList = zulistService.findZulistById(id);
        if (contract1 == null) {
            model.addAttribute("zulist", zuList);
            model.addAttribute("contract", contract);
            model.addAttribute("error", RespCode.CONTRACT_IS_NOT_EXIST);
            model.addAttribute("mainPage", "admin/updatecontract.html");
            return "admin/main1";
        } else {
            ZuList zuList2 = zulistService.findZuList(contract.getHouseCode());
            try {
                contractService.updateContract(contract);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
            model.addAttribute("zulist", zuList2);
            model.addAttribute("contract", contract);
            model.addAttribute("error", RespCode.UPDATE_SUCCESS);
            model.addAttribute("mainPage", "admin/updatecontract.html");
            return "admin/main1";
        }
    }

    /**
     * 管理员终止合同操作：删除合同，插入已退租列表，删除在租列表，删除房屋列表
     *
     * @param houseCode
     * @param model
     * @return
     */
    @RequestMapping("/deleteContract")
    public String deleteContract(@RequestParam String houseCode, Model model) {
        //删除合同
        try {
            contractService.deleteContract(houseCode);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        ZuList zulist = zulistService.findZuList(houseCode);
        Checkout checkout = Checkout.builder()
                .houseCode(houseCode)
                .address(zulist.getAddress())
                .status(RespCode.CHECK_OUT_STATUS_RETIRED_TENNANCY)
                .userlistId(zulist.getUserlistId()).build();
        //插入已退租列表
        try {
            checkoutService.insertCheckout(checkout);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        //更新房屋列表状态为未租赁，以便别的租客继续申请看房
        HouseList houseList = houselistService.findByHouseCode(houseCode);
        houseList.setStatus(UtilEnum.houseListStatus1.getDescription());
        try {
            houselistService.updateHouseStatus(houseList);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        //删除在租列表
        try {
            zulistService.deleteZuList(houseCode);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("error", "checkoutsuccess");
        return "redirect:/zulist/findZuList";
    }

    /**
     * 租客查看合同信息
     *
     * @param houseCode
     * @param model
     * @return
     */
    @RequestMapping("/viewContractByZuke")
    public String viewContractByZuke(@RequestParam String houseCode, Model model) {
        Contract contract = contractService.findContract(houseCode);
        model.addAttribute("contract", contract);
        model.addAttribute("mainPage", "zuke/showcontract.html");
        return "zuke/main";
    }

    /**
     * 合同修改页面点击返回返回到合同合同详情页面
     *
     * @return
     */
    @RequestMapping("/toContract")
    public String toContract(@RequestParam Long id) {
        return "redirect:/contract/viewContractByAdmin?id=" + id + "";
    }

    /**
     * 合同详情页面点击返回返回到在租列表页面
     *
     * @return
     */
    @RequestMapping("/toZulist")
    public String toZulist() {
        return "redirect:/zulist/findZuList";
    }
}
