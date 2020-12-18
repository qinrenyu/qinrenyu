package com.exam.manage.framePage;

import com.exam.manage.asystem.entity.SysMenus;
import com.exam.manage.security.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuPage {

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "common-page/top-menu" }, method = RequestMethod.GET)
	public String showTopMenuPage(Model model, HttpServletRequest request) {

		UserInfo userInfo = "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()) ? null : (UserInfo) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();


		List<SysMenus> list = new ArrayList<SysMenus>();

		if (userInfo != null) {
			LinkedHashMap<String, SysMenus> map = userInfo.getMenuMap();
			for (Map.Entry<String, SysMenus> entry : map.entrySet()) {
				list.add(entry.getValue());
			}
			model.addAttribute("topMenuList", list);
		}
		model.addAttribute("topMenuId", request.getParameter("topMenuId"));
		
		return "common/top-menu";
	}

	@RequestMapping(value = { "common-page/left-menu" }, method = RequestMethod.GET)
	public String showLeftMenuPage(Model model, HttpServletRequest request) {

		String topMenuId =request.getParameter("topMenuId");
		String leftMenuId =request.getParameter("leftMenuId");

		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<SysMenus> list = userInfo.getMenuMap().get(topMenuId).getChildList();
		model.addAttribute("leftMenuList", list);
		if ("1".equals(leftMenuId)){
			model.addAttribute("leftMenuId", list.get(0).getMenuId());
		}else {
			model.addAttribute("leftMenuId", leftMenuId);
		}

		return "common/left-menu";
		
		

	}

	@RequestMapping(value = { "common-page/footer" }, method = RequestMethod.GET)
	public String showFooterPage(Model model, HttpServletRequest request) {

		return "common/footer";
	}
}
