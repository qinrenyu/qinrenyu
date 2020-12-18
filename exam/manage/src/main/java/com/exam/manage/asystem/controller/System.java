package com.exam.manage.asystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class System {
    @RequestMapping(value = "/fistPage")
    public String fistPage(@RequestParam(value = "topMenuId")String topMenuId, Model model) {
        model.addAttribute("topMenuId", topMenuId);
        model.addAttribute("leftMenuId","1");
        return "system/userInfo";
    }

    @RequestMapping(value = "/system/set")
    public String set(@RequestParam(value = "topMenuId")String topMenuId, Model model) {
        model.addAttribute("topMenuId", topMenuId);
        model.addAttribute("leftMenuId","1");
        return "system/menuForm";
    }



}
