package com.javaweb.dao.impl;

import javax.annotation.ManagedBean;

import com.javaweb.dao.INewsDAO;

@ManagedBean
public class NewsDAO extends AbstractDAO implements INewsDAO {

	@Override
	public void testinject() {
		System.out.println("inject in DAO success");
		System.out.println("inject in DAO success");
		System.out.println("inject in DAO success");
		System.out.println("inject in DAO success");
		System.out.println("inject in DAO success");
	}

}
