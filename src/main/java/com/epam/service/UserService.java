package com.epam.service;

import java.util.List;

import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public interface UserService {

	int getUserCount() throws ServiceException;

	List<User> getUsers() throws ServiceException;

	boolean updateUser(String userName, String firstName, String lastName, String phoneNumber) throws ServiceException;

	boolean changePassword(String userName, String password, String newPassword) throws ServiceException;

}
