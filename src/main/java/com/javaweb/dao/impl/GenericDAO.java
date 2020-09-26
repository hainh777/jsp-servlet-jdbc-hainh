package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.javaweb.dao.IGenericDAO;
import com.javaweb.mapper.IRowMapper;

public class GenericDAO<T> implements IGenericDAO<T> {

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

	@Override
	public <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement preStatement = null;
		ResultSet resultset = null;
		try {
			connection = getConnection();
			preStatement = connection.prepareStatement(sql);
			setParameter(preStatement, parameters);
			resultset = preStatement.executeQuery();
			while(resultset.next()) {
				results.add(rowMapper.mapRow(resultset));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(preStatement != null) {
					preStatement.close();
				}
				if(resultset != null) {
					resultset.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}
	private void setParameter(PreparedStatement preStatement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					preStatement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					preStatement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					preStatement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					preStatement.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
