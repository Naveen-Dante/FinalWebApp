package com.epam.service;

import java.util.List;

import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public interface UserService {

	int getUserCount() throws ServiceException;

	List<User> getUsers() throws ServiceException;

}
