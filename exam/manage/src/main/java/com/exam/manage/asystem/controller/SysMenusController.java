package com.exam.manage.asystem.controller;

import com.exam.common.domain.exam.Message;
import com.exam.manage.asystem.entity.SysMenus;
import com.exam.manage.asystem.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/menu")
public class SysMenusController {

    @Autowired
    private SysMenusService sysMenuService;

    @RequestMapping(value = "/list")
    public String menuList(Model model, @RequestParam(value = "topMenuId")String topMenuId, @RequestParam(value = "leftMenuId")String leftMenuId) {
        List<SysMenus> menusList =sysMenuService.findAll();
        model.addAttribute("topMenuId", topMenuId);
        model.addAttribute("leftMenuId", leftMenuId);
        model.addAttribute("menusList", menusList);
        return "system/menuList";
    }


    @RequestMapping(value = "/findAll")
    public String findAll(Model model) {
        List<SysMenus> menusList =sysMenuService.findAll();
        model.addAttribute("menusList", menusList);
        return "system/menuList";
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public Message addUser(@RequestBody SysMenus menus){
        Message msg = new Message();
        try {
            sysMenuService.save(menus);
        } catch (Exception e) {
            msg.setResult(e.getClass().getName());
            e.printStackTrace();
        }
        return msg;
    }
    @RequestMapping(value = "/form")
    public String form(Model model, @RequestParam(value = "topMenuId")String topMenuId, @RequestParam(value = "leftMenuId")String leftMenuId){
        model.addAttribute("topMenuId", topMenuId);
        model.addAttribute("leftMenuId", leftMenuId);
        return "system/menuForm";
    }


}
