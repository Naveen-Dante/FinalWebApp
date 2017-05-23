package com.epam.dao;

import java.util.List;

import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public interface UserDAO {

	List<User> getUsers() throws DAOException;

	int getUserCount() throws DAOException;

}
