package com.epam.service.impl;

import java.util.List;

import com.epam.dao.UserDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.UserDAOImpl;
import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;

public class UserServiceImpl implements UserService{

	private static volatile UserServiceImpl instance;
	private static UserDAO dao;
	
	private UserServiceImpl(){
		dao = UserDAOImpl.getInstance();
	}
	
	public static UserServiceImpl getInstance(){
		if(instance == null){
			synchronized (UserServiceImpl.class) {
				if(instance == null){
					instance = new UserServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public int getUserCount() throws ServiceException {
		int count;
		try{
			count = dao.getUserCount();
		}catch(DAOException e){
			throw new ServiceException("Cannot get the User Count.",e);
		}
		return count;
	}

	@Override
	public List<User> getUsers() throws ServiceException {
		List<User> users;
		try{
			users = dao.getUsers();
		}catch(DAOException e){
			throw new ServiceException("Cannot get the User Count.",e);
		}
		return users;
	}
}
