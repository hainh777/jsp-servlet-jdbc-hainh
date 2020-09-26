package com.javaweb.dao;

import java.util.List;

import com.javaweb.mapper.IRowMapper;

public interface IGenericDAO<T> {
	<T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
}
