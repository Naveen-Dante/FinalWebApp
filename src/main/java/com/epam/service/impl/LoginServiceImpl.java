package com.epam.service.impl;

import com.epam.dao.LoginDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.LoginDAOImpl;
import com.epam.domain.User;
import com.epam.service.LoginService;
import com.epam.service.exception.ServiceException;

public class LoginServiceImpl implements LoginService {

	private static volatile LoginServiceImpl instance;
	private static LoginDAO dao;

	private LoginServiceImpl() {
		dao = LoginDAOImpl.getInstance();
	}

	public static LoginServiceImpl getInstance() {
		if (instance == null) {
			synchronized (LoginServiceImpl.class) {
				if (instance == null)
					instance = new LoginServiceImpl();
			}
		}
		return instance;
	}

	public User getUser(String userName, String password) throws ServiceException {
		User user = null;
		try {
			user = dao.getUser(userName, password);
		} catch (DAOException e) {
			throw new ServiceException("Unable to get the UserData.",e);
		}
		return user;
	}

}
