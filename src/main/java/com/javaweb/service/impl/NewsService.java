package com.javaweb.service.impl;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import com.javaweb.dao.INewsDAO;
import com.javaweb.service.INewsService;

@ManagedBean
public class NewsService implements INewsService{

	@Inject
	INewsDAO newsDao;
	
	@Override
	public void testinject() {
		newsDao.testinject();
	}


}
