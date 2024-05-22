package com.gdpu.controller;
import com.gdpu.common.ActiveUser;
import com.gdpu.common.ResultObj;
import com.gdpu.common.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping("login")
    public ResultObj login(String loginName, String password){

        Subject subject = SecurityUtils.getSubject();
        //token认证
        AuthenticationToken token = new UsernamePasswordToken(loginName, password);
        try{
            //对用户进行认证登陆
            subject.login(token);
            //通过subject获取以认证活动的user
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            //将user存储到session中
            WebUtils.getSession().setAttribute("user",activeUser.getManager());
            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e){
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }
}
