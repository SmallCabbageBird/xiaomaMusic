package com.xiaoma.music.service;

import com.xiaoma.music.entity.User;
/**
 * 用户service接口
 * @author lenovo
 *
 */
public interface IUserService {
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	public String checkUserName(String username);
	
	/**
	 * 注册
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User login(User user);

}
