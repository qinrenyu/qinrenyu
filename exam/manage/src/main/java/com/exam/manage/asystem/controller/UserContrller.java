package com.exam.manage.asystem.controller;

import com.exam.common.domain.exam.Message;
import com.exam.common.domain.user.User;
import com.exam.manage.asystem.entity.SysMenus;
import com.exam.manage.asystem.service.SysMenusService;
import com.exam.manage.framePage.MenuNeed;
import com.exam.manage.security.UserInfo;
import com.exam.manage.asystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserContrller {
	@Autowired
	private UserService userService;
	@Autowired
	private SysMenusService sysMenusService;
	@RequestMapping(value = "/index")
	public String userIndex(Model model, HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user=userService.get(userInfo.getUserid());
		SysMenus menus=sysMenusService.selectByMenuPath("user/info");
		model.addAttribute("userId", user.getUserId());
		model.addAttribute("topMenuId", menus.getMenuParentId());
		model.addAttribute("leftMenuId", menus.getMenuId());
		return "system/userInfo";
	}

	@RequestMapping(value = "/info")
	public String userInfo(Model model, @RequestParam(value = "topMenuId")String topMenuId, @RequestParam(value = "leftMenuId")String leftMenuId) {
		model.addAttribute("topMenuId", topMenuId);
		model.addAttribute("leftMenuId", leftMenuId);
		return "system/userInfo";
	}

	@RequestMapping(value = "/get")
	public String get(Model model,@RequestParam(value = "userId")String userId) {
		User user=userService.get(userId);
		model.addAttribute("user", user);
		return "system/userForm";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Message addUser(@RequestBody User user){
		Message msg = new Message();
		try {
			userService.save(user);
		} catch (Exception e) {
			msg.setResult(e.getClass().getName());
			e.printStackTrace();
		}
		return msg;
	}
}
