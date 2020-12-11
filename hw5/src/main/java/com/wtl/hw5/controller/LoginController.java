package com.wtl.hw5.controller;

import com.wtl.hw5.Data.LoginInfor;
import com.wtl.hw5.Service.CheckLogin;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    // 请求登录界面
    @RequestMapping("/login")
    public String login(LoginInfor user, Model model) {
        model.addAttribute("user", user); // 向view模型发送infor数据
        return "login";
    }

    // 登录检测界面
    @SneakyThrows
    @PostMapping("/checklogin")
    public String checkLogin(LoginInfor user, Model model, HttpServletRequest request) {
        if (true == CheckLogin.Check(user)) { // 通过校验
            user.setMessage("OK");
            request.getSession().setAttribute("login", "OK");   // 设置标识
            return "redirect:/main";                                // 重定向到main界面
        } else {  // 没通过校验
            user.setMessage("用户名或密码错误");
            user.setPassword("");
            return login(user, model);
        }
    }

    // 登录检测界面，进行重定向
    @SneakyThrows
    @GetMapping("/checklogin")
    public String redirectLogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))  // 没登录重定向到登录界面
            return "redirect:/login";
        else   // 登录了重定向到主界面
            return "redirect:/main";
    }

}
