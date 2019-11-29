package com.xiaoma.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoma.music.dao.IUserDao;
import com.xiaoma.music.entity.User;
import com.xiaoma.music.service.IUserService;
/**
 * 用户service实现类
 * @author lenovo
 *
 */
@Service("userService")
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	/**
	 * 检查用户名
	 */
	@Override
	public String checkUserName(String username) {
			
		
		return userDao.checkUserName(username) > 0 ? "true" : "false";
	
	}
	
	/**
	 * 用户注册
	 */
	@Override
	public void register(User user) {
		
		userDao.addUser(user);
		
	}

	/**
	 * 用户登录
	 */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User login(User user) {
		
		User dbUser = userDao.findByUsername(user.getUsername());
		
		if(dbUser == null){
			
			return null;
		}
		
		if(user.getPassword().equals(dbUser.getPassword())){
			
			return dbUser;
		}
		
		return null;
	}

}
