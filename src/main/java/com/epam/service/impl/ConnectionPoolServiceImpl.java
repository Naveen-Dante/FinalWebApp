package com.epam.service.impl;

import com.epam.dao.IConnectionPool;
import com.epam.dao.pool.ConnectionPool;
import com.epam.service.ConnectionPoolService;

public class ConnectionPoolServiceImpl implements ConnectionPoolService {

	private static volatile ConnectionPoolServiceImpl instance;
	
	private static IConnectionPool dao;
	
	private ConnectionPoolServiceImpl(){
		dao = ConnectionPool.getInstance();
	}
	
	public static ConnectionPoolServiceImpl getInstance(){
		if(instance == null){
			synchronized (ConnectionPoolServiceImpl.class) {
				if(instance == null){
					instance = new ConnectionPoolServiceImpl();
				}
			}
		}
		return instance;
	}
	
	public void init() {
		dao.init();
	}

	@Override
	public void destroy() {
		dao.destroy();	
	}
}
