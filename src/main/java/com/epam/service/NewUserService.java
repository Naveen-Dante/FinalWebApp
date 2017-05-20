package com.epam.service;

import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public interface NewUserService {

	boolean addNewUser(User user, String password) throws ServiceException;
}
