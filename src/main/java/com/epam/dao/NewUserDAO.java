package com.epam.dao;

import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public interface NewUserDAO {

	boolean addNewUser(User user, String password) throws DAOException;

}
