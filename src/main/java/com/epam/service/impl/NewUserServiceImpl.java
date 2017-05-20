package com.epam.service.impl;

import com.epam.dao.NewUserDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.NewUserDAOImpl;
import com.epam.domain.User;
import com.epam.service.NewUserService;
import com.epam.service.exception.ServiceException;

public class NewUserServiceImpl implements NewUserService {

	private static volatile NewUserServiceImpl instance;
	private static NewUserDAO dao;
	
	private NewUserServiceImpl(){
		dao = NewUserDAOImpl.getInstance();
	}
	
	public static NewUserServiceImpl getInstance(){
		if(instance == null){
			synchronized (NewUserServiceImpl.class) {
				if(instance == null)
					instance = new NewUserServiceImpl();
			}
		}
		return instance;
	}
	
	@Override
	public boolean addNewUser(User user, String password) throws ServiceException{
		boolean success = false;
		try {
			success = dao.addNewUser(user,password);
		} catch (DAOException e) {
			throw new ServiceException("Can't retrieve Data from DB.",e);
		}
		return success;
	}


}
