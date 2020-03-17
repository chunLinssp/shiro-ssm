package org.shiro.maven.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.shiro.maven.common.constant.Base;
import org.shiro.maven.common.exception.CustomException;
import org.shiro.maven.po.SysUser;
import org.shiro.maven.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    SysService sysService;

    @RequestMapping("/index")
    public String test() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception {

        //如果登录失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

        //根据shiro返回的异常路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛出异常处理器
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名或密码错误");
            } else {
                throw new Exception();
            }
        }

        //登录成功，shiro会自动跳转
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String rememberMe = (String) request.getAttribute("rememberMe");
        if(username != null && password != null){
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
                SysUser sysUser = sysService.findByUserName(username);
                subject.getSession().setAttribute(Base.CURRENT_USER,sysUser);
                return "welcome";
            } catch (UnknownAccountException e) {
                String message = e.getMessage();
                throw new CustomException(message);
            } catch (LockedAccountException e) {
                String message = e.getMessage();
                throw new CustomException(message);
            } catch (AuthenticationException e) {
                String message = e.getMessage();
                throw new CustomException(message);
            } catch (Exception e) {
                String message = e.getMessage();
                throw new CustomException("未知错误");
            }
        }



        //登录失败返回login页面
        return "login";

    }


}
