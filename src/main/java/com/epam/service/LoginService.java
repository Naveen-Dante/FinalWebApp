package com.epam.service;

import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public interface LoginService {

	User getUser(String userName, String password) throws ServiceException;
	
}
