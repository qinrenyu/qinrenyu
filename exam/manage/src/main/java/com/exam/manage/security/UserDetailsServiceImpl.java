package com.exam.manage.security;

import com.exam.common.domain.user.User;
import com.exam.manage.asystem.entity.SysAuthority;
import com.exam.manage.asystem.entity.SysMenus;
import com.exam.manage.asystem.dao.SysAuthorityMapper;
import com.exam.manage.asystem.dao.SysAuthorityMenuMapper;
import com.exam.manage.asystem.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserInfo userInfo;
	@Autowired
	public UserMapper userMapper;
	@Autowired
	public SysAuthorityMapper sysAuthorityMapper;

	@Autowired
	private SysAuthorityMenuMapper sysAuthorityMenuMapper;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = userMapper.getUserByName(username);
		if(user == null||user.getCompany().equals("0"))
			throw new UsernameNotFoundException("user not found!");

		List<SysAuthority> permissions = sysAuthorityMapper.findByUserId(user.getUserId());
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		LinkedHashMap<String, SysMenus> menuMap=new LinkedHashMap<>();
		String roles="";
		for (SysAuthority permission : permissions) {
			if (permission != null ) {
				for (SysMenus menu:sysAuthorityMenuMapper.findMenuListByAuthorityId(permission.getAuthorityId())){
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getPower());
					grantedAuthorities.add(grantedAuthority);
					if(menu.getMenuParentId().equals("-1")){
						menuMap.put(menu.getMenuId(), menu);
					}
				}
			}
			roles=roles+permission.getAuthorityName();
		}
		//roles=角色代码
		userInfo = new UserInfo(username,user.getPassword(),true,true,true,true,grantedAuthorities);
		userInfo.setUserid(user.getUserId());
		userInfo.setRolesName(roles);
		userInfo.setMenuMap(menuMap);
		return userInfo;
	}


}
