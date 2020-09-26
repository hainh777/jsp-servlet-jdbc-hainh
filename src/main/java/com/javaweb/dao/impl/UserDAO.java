package com.javaweb.dao.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import com.javaweb.dao.IUserDAO;
import com.javaweb.mapper.impl.UserMapper;
import com.javaweb.model.UserModel;

@ManagedBean
public class UserDAO extends GenericDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), username, password, status);
		return user.isEmpty() ? null : user.get(0);
	}

}
