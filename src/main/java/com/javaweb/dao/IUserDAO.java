package com.javaweb.dao;

import com.javaweb.model.UserModel;

public interface IUserDAO {
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
