package com.epam.dao;

import java.sql.Connection;


public interface IConnectionPool {

	void init();
	Connection getConnection();
	void returnConnection(Connection connection);
	void destroy();
	
}
