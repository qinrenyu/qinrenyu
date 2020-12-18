package com.exam.manage.asystem.dao;

import com.exam.common.domain.user.Department;
import com.exam.common.domain.user.Group;
import com.exam.common.domain.user.Role;
import com.exam.common.domain.user.User;
import com.exam.common.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {

	/**
	 * 根据id获取用户信息
	 * 
	 * @param userid
	 * @return
	 */
	public User get(String userid);
	/**
	 * 根据用户名称查询用户基本信息
	 *
	 * @param username
	 * @return
	 */
	public User getUserByName(@Param("username") String username);
	/**
	 * 根据用户名称查询用户基本信息
	 *
	 * @param user
	 * @return
	 */
	public int insert(User user);
	public int update(User user);

}
