package com.lanou.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.entity.User;
import com.lanou.mapper.UserMapper;
import com.lanou.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	



	@Override
	public User login(String name, String password) {
		Map<String,Object> map = new HashMap<>();
		map.put("username", name);
		map.put("password", password);
		return userMapper.login(map);
	}




	@Override
	public User selectByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectByName(name);
	}










	

}
