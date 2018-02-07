package com.lanou.service;

import com.lanou.entity.User;

public interface UserService {
	
	/**
	 * 根据用户名和密码登录
	 * @param name
	 * @param password
	 * @return
	 */
	User  login(String name ,String password);
	
	
	/**
	 * 根据名字查找用户
	 * @return
	 */
	User  selectByName(String name);
	
}
