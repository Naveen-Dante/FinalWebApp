package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.dao.IConnectionPool;
import com.epam.dao.NewUserDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.pool.ConnectionPool;
import com.epam.dao.utility.Utility;
import com.epam.domain.User;

public class NewUserDAOImpl implements NewUserDAO {

	private static final String USER = "user";
	private static volatile NewUserDAOImpl instance;
	private static final IConnectionPool POOL = ConnectionPool.getInstance();
	private static final String INSERT_QUERY = "INSERT INTO `user` "
			+ "(`username`, `firstname`, `lastname`, `email`, `password`, `phonenumber`, `isadmin`) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?);";
	private static final String IS_USER_NAME_QUERY = "SELECT EXISTS ("
			+ "SELECT 1 FROM `user` WHERE `username` = ?)" + " AS user ";

	private NewUserDAOImpl() {

	}

	public static NewUserDAOImpl getInstance() {
		if (instance == null) {
			synchronized (NewUserDAOImpl.class) {
				if (instance == null)
					instance = new NewUserDAOImpl();
			}
		}
		return instance;
	}

	public boolean addNewUser(User user, String password) throws DAOException {
		// TODO Auto-generated method stub
		boolean success = false;
		Connection connection = POOL.getConnection();
		PreparedStatement insertQuery = null;
		try {
			if (isUser(user, connection)) {
				insertQuery = connection.prepareStatement(INSERT_QUERY);
				insertQuery.setString(1, user.getUserName());
				insertQuery.setString(2, user.getFirstName());
				insertQuery.setString(3, user.getLastName());
				insertQuery.setString(4, user.getEmail());
				insertQuery.setString(5, password);
				insertQuery.setString(6, user.getPhoneNumber().toString());
				insertQuery.setInt(7, user.isAdmin() == true ? 1 : 0);
				int rows = insertQuery.executeUpdate();
				success = (rows > 0 ? true : false);
				
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to Create New User", e);
		} finally {
			if(success){
				Utility.closeStatement(insertQuery);
			}
			POOL.returnConnection(connection);
		}
		return success;
	}

	private boolean isUser(User user, Connection connection) throws SQLException {
		boolean isAvailable = true;
		PreparedStatement validateExistanceQuery = null;
		validateExistanceQuery = connection.prepareStatement(IS_USER_NAME_QUERY);
		validateExistanceQuery.setString(1, user.getUserName());
		ResultSet rs = validateExistanceQuery.executeQuery();
		while (rs.next()) {
			if (rs.getInt(USER) == 1) {
				isAvailable = false;
			}
		}
		Utility.closeStatement(validateExistanceQuery);
		return isAvailable;
	}

}
