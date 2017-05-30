package com.epam.service;

import java.util.List;

import com.epam.domain.AdminBook;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.domain.UserBook;
import com.epam.service.exception.ServiceException;


public interface BookService {

	List<Book> getBooks(String username, String language) throws ServiceException;

	List<Book> searchBook(String searchText, String userName) throws ServiceException;

	BookInfo getBookInfo(int bookId, String language) throws ServiceException;
	
	List<UserBook> getAllBooks(String language) throws ServiceException;

	int getBooksCount() throws ServiceException;

	List<AdminBook> getAllBooks() throws ServiceException;

	boolean addNewBook(BookInfo book) throws ServiceException;

	boolean UpdateBook(BookInfo book) throws ServiceException;

	boolean removeBook(int bookId, String language) throws ServiceException;

	List<UserBook> getAllBooks(String language, String userName) throws ServiceException;

	boolean addFavouriteBook(int bookId, String userName) throws ServiceException;

	List<Book> getFavourites(String language, String userName) throws ServiceException;

	boolean removeFavouriteBook(int bookId, String userName) throws ServiceException;

	List<Book> getTopBooks(String language) throws ServiceException;

	Book getTopBook() throws ServiceException;
}
