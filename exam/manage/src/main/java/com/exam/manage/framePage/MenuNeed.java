package com.exam.manage.framePage;

import com.exam.manage.asystem.entity.SysMenus;
import com.exam.manage.asystem.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;

public class MenuNeed {
    @Autowired
    private SysMenusService sysMenusService;

    public static MenuNeed menuNeed;

    @PostConstruct
    public void init() {
        menuNeed = this;
    }
    public static void returnMenuNeed(Model model,String url){
        SysMenus menus=menuNeed.sysMenusService.selectByMenuPath(url);
        model.addAttribute("topMenuId", menus.getMenuParentId());
        model.addAttribute("leftMenuId", menus.getMenuId());
    }
}
