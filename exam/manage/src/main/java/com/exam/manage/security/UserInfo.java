package com.exam.manage.security;

import com.exam.common.domain.user.Role;
import com.exam.manage.asystem.entity.SysMenus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;


public class UserInfo extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userid;
	private List<Role> roleList;
	private String trueName;
	private String rolesName;
	private String enabled;
	private int fieldId;
	private String fieldName;
	private String email;
	private Date lastLoginTime;
	private Date loginTime;
	private Set<String> menuIds;
	private LinkedHashMap<String, SysMenus> menuMap;
	private HashMap<String,Role> roleMap;
	public HashMap<String, Role> getRoleMap() {
		return roleMap;
	}

	public Set<String> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Set<String> menuIds) {
		this.menuIds = menuIds;
	}

	public void setRoleMap(HashMap<String, Role> roleMap) {
		this.roleMap = roleMap;
	}

	public LinkedHashMap<String, SysMenus> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(LinkedHashMap<String, SysMenus> menuMap) {
		this.menuMap = menuMap;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

}
