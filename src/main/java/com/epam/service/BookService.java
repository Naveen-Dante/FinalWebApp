package com.epam.service;

import java.util.List;

import com.epam.domain.AdminBook;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.service.exception.ServiceException;


public interface BookService {

	List<Book> getBooks(String username, String language) throws ServiceException;

	List<Book> searchBook(String searchText, String userName) throws ServiceException;

	BookInfo getBookInfo(int bookId, String language) throws ServiceException;
	
	List<Book> getAllBooks(String language) throws ServiceException;

	int getBooksCount() throws ServiceException;

	List<AdminBook> getAllBooks() throws ServiceException;
}
