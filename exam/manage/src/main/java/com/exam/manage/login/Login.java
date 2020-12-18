package com.exam.manage.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("result", "无效的用户名或者密码");
        return "login";
    }

}
