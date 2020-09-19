package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.javaweb.dao.IGenericDAO;

public class AbstractDAO implements IGenericDAO {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		String user = resourceBundle.getString("user");
		String password = resourceBundle.getString("password");
		String url = resourceBundle.getString("url");
		try {
			Class.forName(resourceBundle.getString("driverName"));
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
