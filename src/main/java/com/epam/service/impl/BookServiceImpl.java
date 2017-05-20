package com.epam.service.impl;

import java.util.List;

import com.epam.dao.BookDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.BookDAOImpl;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;


public class BookServiceImpl implements BookService{

	private static BookDAO dao;
	private static volatile BookServiceImpl instance;
	
	private BookServiceImpl(){
		dao = BookDAOImpl.getInstance();
	}
	
	public static BookServiceImpl getInstance(){
		if(instance == null){
			synchronized (BookServiceImpl.class) {
				if(instance == null)
					instance = new BookServiceImpl();
			}
		}
		return instance;
	}

	public List<Book> getBooks(String username, String language) throws ServiceException {
		List<Book> books;
		try{
			books = dao.getBooks(username,language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	public List<Book> searchBook(String searchText, String userName, String search, String booklanguage, String language) throws ServiceException {
		List<Book> books;
		try{
			books = dao.searchBooks(searchText, userName,search,booklanguage,language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	public BookInfo getBookInfo(int bookId, String language) throws ServiceException {
		BookInfo books;
		try{
			books = dao.searchBookInfo(bookId,language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	@Override
	public List<Book> getAllBooks(String language) throws ServiceException {
		List<Book> books;
		try{
			books = dao.getAllBooks(language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}
}
