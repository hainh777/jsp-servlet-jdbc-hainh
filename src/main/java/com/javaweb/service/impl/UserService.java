package com.javaweb.service.impl;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import com.javaweb.dao.IUserDAO;
import com.javaweb.model.UserModel;
import com.javaweb.service.IUserService;

@ManagedBean
public class UserService implements IUserService{
	@Inject IUserDAO userDao;
	
	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		return userDao.findByUsernameAndPasswordAndStatus(username, password, status);
	}

}
