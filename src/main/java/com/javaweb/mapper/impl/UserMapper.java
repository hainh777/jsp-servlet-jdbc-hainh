package com.javaweb.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.mapper.IRowMapper;
import com.javaweb.model.RoleModel;
import com.javaweb.model.UserModel;

public class UserMapper implements IRowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel user = new UserModel();
		try {
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setFullName(resultSet.getString("fullname"));
			user.setPassword(resultSet.getString("password"));
			user.setStatus(resultSet.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (SQLException e) {
			return null;
		}
		return user;
	}

}
