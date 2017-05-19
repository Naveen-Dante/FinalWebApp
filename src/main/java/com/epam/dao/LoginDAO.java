package com.epam.dao;

import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public interface LoginDAO {

	User getUser(String userName, String password) throws DAOException;

}
