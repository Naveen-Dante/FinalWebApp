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

	@Override
	public boolean updateUser(String userName, String firstName, String lastName, String phoneNumber)
			throws ServiceException {
		boolean status = false;
		try{
			status =  dao.updateUser(userName, firstName, lastName, phoneNumber);
		}catch (DAOException e) {
			throw new ServiceException("Can't Update the User Data",e);
		}
		return status;
	}

	@Override
	public boolean changePassword(String userName, String password, String newPassword) throws ServiceException {
		boolean status = false;
		try{
			status =  dao.changePassword(userName, password, newPassword);
		}catch (DAOException e) {
			throw new ServiceException("Can't change the User password",e);
		}
		return status;
	}
}
