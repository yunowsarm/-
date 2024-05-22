package com.gdpu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SystemController {

    @RequestMapping("toLogin")
    public String toLogin(){
        return "/index/login";
    }

    @RequestMapping("index")
    public String index(){
        return "/index/index";
    }

    @RequestMapping("toDesktopManager")
    public String toDesktopManager(){
        return "/index/desktopManager";
    }

    @RequestMapping("toRoleManager")
    public String toRoleManager(){
        return "/role/roleManager";
    }

    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "/user/userManager";
    }

    @RequestMapping("toGoodsManager")
    public String toGoodsManager(){
        return "/goods/goodsManager";
    }

    @RequestMapping("toProviderManager")
    public String toProviderManager(){
        return "/provider/providerManager";
    }

    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "/customer/customerManager";
    }

    @RequestMapping("toInputManager")
    public String toInputManager(){
        return "/input/inputManager";
    }

    @RequestMapping("toOutputManager")
    public String toOutputManager(){
        return "/output/outputManager";
    }

    @RequestMapping("toWarehouseManager")
    public String toWarehouseManager(){
        return "/warehouse/warehouseManager";
    }

}
