package com.epam.dao;

import java.util.List;

import com.epam.dao.exception.DAOException;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;


public interface BookDAO {

	List<Book> getBooks(String username, String language) throws DAOException;

	List<Book> searchBooks(String searchText, String userName) throws DAOException;

	BookInfo searchBookInfo(int bookId, String language) throws DAOException;
	
	List<Book> getAllBooks(String language) throws DAOException;

	
}
