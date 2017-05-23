package com.epam.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.dao.UserDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.pool.ConnectionPool;
import com.epam.dao.utility.Utility;
import com.epam.domain.User;

public class UserDAOImpl implements UserDAO {

	private static final String USER_COUNT = "count";
	private static final String PHONENUMBER = "phonenumber";
	private static final String IS_ADMIN = "isadmin";
	private static final String EMAIL = "email";
	private static final String USERNAME = "username";
	private static final String LASTNAME = "lastname";
	private static final String FIRSTNAME = "firstname";
	private static volatile UserDAOImpl instance;
	private static final ConnectionPool POOL = ConnectionPool.getInstance();
	private static final String SELECT_QUERY = "SELECT * FROM user";
	private static final String USERS_COUNT_QUERY = "SELECT count(*) AS count FROM user";

	private UserDAOImpl() {

	}

	public static UserDAOImpl getInstance() {
		if (instance == null) {
			synchronized (UserDAOImpl.class) {
				if (instance == null)
					instance = new UserDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public List<User> getUsers() throws DAOException {
		List<User> users = new ArrayList<>();
		Connection connection = POOL.getConnection();
		PreparedStatement selectQuery = null;
		try {
			selectQuery = connection.prepareStatement(SELECT_QUERY);
			ResultSet rs = selectQuery.executeQuery();
			while (rs.next()) {
				if (rs.getInt(IS_ADMIN) == 0) {
					User user = new User();
					user.setFirstName(rs.getString(FIRSTNAME));
					user.setLastName(rs.getString(LASTNAME));
					user.setUserName(rs.getString(USERNAME));
					user.setEmail(rs.getString(EMAIL));
					user.setType(rs.getInt(IS_ADMIN) == 0 ? false : true);
					user.setPhoneNumber(new BigInteger(rs.getString(PHONENUMBER)));
				}

			}
		} catch (SQLException e) {
			throw new DAOException("Unable to find the user..", e);
		} finally {
			Utility.closeStatement(selectQuery);
			POOL.returnConnection(connection);
		}
		return users;
	}

	@Override
	public int getUserCount() throws DAOException {
		int count = -1;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(USERS_COUNT_QUERY);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				count = set.getInt(USER_COUNT);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot get the user Count", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return count;
	}

}
