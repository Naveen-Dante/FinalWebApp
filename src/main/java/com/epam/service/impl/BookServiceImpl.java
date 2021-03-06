package com.epam.service.impl;

import java.util.List;

import com.epam.dao.BookDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.BookDAOImpl;
import com.epam.domain.AdminBook;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.domain.UserBook;
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

	public List<Book> searchBook(String searchText, String language) throws ServiceException {
		List<Book> books;
		try{
			books = dao.searchBooks(searchText, language);
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
	public List<UserBook> getAllBooks(String language) throws ServiceException {
		List<UserBook> books;
		try{
			books = dao.getAllBooks(language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	@Override
	public int getBooksCount() throws ServiceException {
		int count;
		try{
			count = dao.getBooksCount();
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return count;
	}

	@Override
	public List<AdminBook> getAllBooks() throws ServiceException {
		List<AdminBook> books;
		try{
			books = dao.getAllBooks();
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	@Override
	public boolean addNewBook(BookInfo book) throws ServiceException {
		boolean success;
		try{
			success = dao.addNewBook(book);
		}catch(DAOException e){
			throw new ServiceException("Cannot add book",e);
		}
		return success;
	}

	@Override
	public boolean UpdateBook(BookInfo book) throws ServiceException {
		boolean success;
		try{
			success = dao.updateBook(book);
		}catch(DAOException e){
			throw new ServiceException("Cannot add book",e);
		}
		return success;
	}

	@Override
	public boolean removeBook(int bookId, String language) throws ServiceException {
		boolean success;
		try{
			success = dao.removeBook(bookId,language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return success;
	}

	@Override
	public List<UserBook> getAllBooks(String language, String userName) throws ServiceException {
		List<UserBook> books;
		try{
			books = dao.getAllBooks(language, userName);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	@Override
	public boolean addFavouriteBook(int bookId, String userName) throws ServiceException {
		boolean success;
		try{
			success = dao.addFavouriteBook(bookId, userName);
		}catch(DAOException e){
			throw new ServiceException("Cannot add book",e);
		}
		return success;
	}

	@Override
	public List<Book> getFavourites(String language, String userName) throws ServiceException {
		List<Book> books;
		try{
			books = dao.getFavourites(language, userName);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	@Override
	public boolean removeFavouriteBook(int bookId, String userName) throws ServiceException {
		System.out.println("In Service");
		boolean success;
		try{
			success = dao.removeFavouriteBook(bookId, userName);
		}catch(DAOException e){
			throw new ServiceException("Cannot add book",e);
		}
		return success;
	}

	@Override
	public List<Book> getTopBooks(String language) throws ServiceException {
		List<Book> books;
		try{
			books = dao.getTopBooks(language);
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Books..",e);
		}
		return books;
	}

	@Override
	public Book getTopBook() throws ServiceException {
		Book book;
		try{
			book = dao.getTopBooks();
		}catch (DAOException e) {
			throw new ServiceException("Unable to fetch Most Rated Book..",e);
		}
		return book;
	}
}
