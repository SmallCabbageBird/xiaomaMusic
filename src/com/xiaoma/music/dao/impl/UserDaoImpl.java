package com.xiaoma.music.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.xiaoma.music.dao.IUserDao;
import com.xiaoma.music.entity.User;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 检查用户名是否存在
	 */
	@Override
	public int checkUserName(String username) {
		
		List<Long> count = (List<Long>) hibernateTemplate.find("select count(username) from User where username = ?",username);
		
		return count.isEmpty() ? 0 : count.get(0).intValue();
	}
	
	
	/**
	 * 添加用户
	 */
	@Override
	public void addUser(User user) {
		
		hibernateTemplate.save(user);
	}
	/**
	 * 通过用户名找用户
	 */
	@Override
	public User findByUsername(String username) {
			
		List<User> userList =  (List<User>) hibernateTemplate.find("from User where username = ?",username);

		return userList.size() == 0 ? null : userList.get(0);
		
	
	}

}
