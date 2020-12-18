package com.exam.manage.asystem.service;

import com.exam.common.domain.user.Department;
import com.exam.common.domain.user.Group;
import com.exam.common.domain.user.Role;
import com.exam.common.domain.user.User;
import com.exam.common.util.Page;
import com.exam.common.util.StandardPasswordEncoderForSha1;
import com.exam.manage.asystem.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ocelot
 * @date 2014年6月8日 下午8:21:31
 */
@Service
public class UserService{

	@Autowired
	public UserMapper userMapper;


	public User get(String userid) {
		return userMapper.get(userid);
	}
	public void save(User user) {
		if (StringUtils.isEmpty(user.getUserId())){
			userMapper.insert(user);
		}else{
			userMapper.update(user);
		}

	}

}
