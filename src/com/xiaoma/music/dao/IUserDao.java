package com.xiaoma.music.dao;

import com.xiaoma.music.entity.User;



/**
 * 用户dao接口
 * @author lenovo
 *
 */
public interface IUserDao {
	
	/**
	 * 检查用户名是否已存在
	 * @param username
	 * @return
	 */
	public int checkUserName(String username);
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	
	/**
	 * 通过用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
}
