package com.epam.dao;

import java.util.List;

import com.epam.dao.exception.DAOException;
import com.epam.domain.AdminBook;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.domain.UserBook;


public interface BookDAO {

	List<Book> getBooks(String username, String language) throws DAOException;

	List<Book> searchBooks(String searchText, String userName) throws DAOException;

	BookInfo searchBookInfo(int bookId, String language) throws DAOException;
	
	List<UserBook> getAllBooks(String language) throws DAOException;

	int getBooksCount() throws DAOException;

	List<AdminBook> getAllBooks() throws DAOException;

	boolean addNewBook(BookInfo book) throws DAOException;

	boolean updateBook(BookInfo book) throws DAOException;

	boolean removeBook(int bookId, String language) throws DAOException;

	List<UserBook> getAllBooks(String language, String userName) throws DAOException;

	boolean addFavouriteBook(int bookId, String userName) throws DAOException;

	List<Book> getFavourites(String language, String userName) throws DAOException;

	boolean removeFavouriteBook(int bookId, String userName) throws DAOException;

	List<Book> getTopBooks(String language) throws DAOException;

	Book getTopBooks() throws DAOException;

	
}
