package com.exam.manage.framePage;

import com.exam.manage.asystem.entity.SysMenus;
import com.exam.manage.asystem.dao.SysMenusMapper;
import com.exam.manage.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


@Controller
public class BasePage {


	@Autowired
	private SysMenusMapper sysMenusMapper;

	/**
	 * 网站首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model, HttpServletRequest request) {

		return "redirect:home";
	}
	/**
	 * 用户登录界面
	 * @param model
	 * @param result
	 * @return
	 */
	@RequestMapping(value = { "/user-login-page" }, method = RequestMethod.GET)
	public String loginPage(Model model, @RequestParam(value = "result", required = false, defaultValue = "") String result) {
		if("failed".equals(result)){
			model.addAttribute("result", "无效的用户名或者密码");
		}
		return "login";
	}
	/**
	 * 管理员登陆
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/admin/home" }, method = RequestMethod.GET)
	public String adminHomePage(Model model, HttpServletRequest request) {

		return "redirect:/admin/dashboard";
	}
	
	
	/**
	 * 管理员登陆
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/teacher/home" }, method = RequestMethod.GET)
	public String teacherHomePage(Model model, HttpServletRequest request) {

		return "redirect:/teacher/dashboard";
	}

	/**
	 * 判断不同角色返回的页面
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String directToBaseHomePage(Model model, HttpServletRequest request) {
		if (SecurityContextHolder.getContext().getAuthentication() == null){
			return "login";
		}
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().endsWith("anonymousUser")){
			return "login";
		}
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> grantedAuthorities = userInfo.getAuthorities();
		String userRoles=userInfo.getRolesName();
		//这里获取用户的菜单信息
		if (StringUtils.isEmpty(userRoles)||userRoles.contains("ROLE_STUDENT")) {

			SecurityContextHolder.getContext().setAuthentication(null);
			model.addAttribute("result", "学生没有后台管理系统权限");
			return "login";
		} else {
			LinkedHashMap<String, SysMenus> map = userInfo.getMenuMap();

			for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
				SysMenus sysMenus=map.get(iter.next());
				List<SysMenus> childList= sysMenusMapper.findByParentIdsLike("%"+sysMenus.getMenuId()+"%");
				sysMenus.setChildList(childList);
			}
			return "redirect:user/index";
		}
	}
	
	
	@RequestMapping(value = "/user-detail/{userName}", method = RequestMethod.GET)
	public String userInfoPage(Model model, HttpServletRequest request, @PathVariable("userName") String userName) {

		return "redirect:/admin/home";
	}
	
	public enum UserType {
		admin, teacher, student;
	}

	
	
}








