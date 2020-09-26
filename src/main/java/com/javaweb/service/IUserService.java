package com.javaweb.service;

import com.javaweb.model.UserModel;

public interface IUserService {
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
