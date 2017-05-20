package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.dao.BookDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.pool.ConnectionPool;
import com.epam.dao.utility.Utility;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.domain.BookType;


public class BookDAOImpl implements BookDAO{
	
	private static final String PAPER_BOUND = "paper";

	private static final String DESCRIPTION = "description";

	private static final String IMAGE_URL = "imageurl";

	private static final String BOOK_AUTHOR = "author";

	private static final String BOOK_TITLE = "title";

	private static final String BOOK_ID = "id";
	
	private static final String TYPE = "type";

	private static volatile BookDAOImpl instance;
	
	private static final ConnectionPool POOL = ConnectionPool.getInstance();

	private static final String SELECT_QUERY = "select book.id,book.title,book.author,book.type "
			+ "from book join user_has_book on book.id=user_has_book.book_id  "
			+ "join gg4hyz6gpvflvqpc.user on user_has_book.user_id=gg4hyz6gpvflvqpc.user.id "
			+ "where user.username=? and book.language=?";
	
	private static final String SEARCH_QUERY = "select t.id,title,author,type from (SELECT * FROM book "+
			"WHERE MATCH (book.title,book.author,book.description) "+
			"AGAINST (?)) as t join user_has_book on t.id=user_has_book.book_id "+  
			"join user on user_has_book.user_id=user.id where username=? and language=?";
	
	private static final String SELECT_ALL_QUERY = "select * from book where language=?";
	
	private static final String SELECT_BOOK_QUERY = "select * from book where id=? and language=?";
	
	private BookDAOImpl(){
		
	}
	
	public static BookDAOImpl getInstance(){
		if(instance == null){
			synchronized (BookDAOImpl.class) {
				if(instance == null)
					instance = new BookDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public List<Book> getAllBooks(String language) throws DAOException{
		List<Book> books = new ArrayList<Book>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement(SELECT_ALL_QUERY);
			statement.setString(1, language);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER: BookType.EBOOK);
				books.add(book);
			}
		}catch (SQLException e) {
			throw new DAOException("Unable to retrieve data",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
		
	}
	public List<Book> getBooks(String username, String language) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setString(1, username);
			statement.setString(2, language);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER: BookType.EBOOK);
				books.add(book);
			}
		}catch (SQLException e) {
			throw new DAOException("Unable to retrieve data",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	public List<Book> searchBooks(String searchText, String userName, String search, String booklanguage, String language)
			throws DAOException {
		List<Book> books = new ArrayList<Book>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement(SEARCH_QUERY);
			statement.setString(2, userName);
			statement.setString(1, searchText);
			statement.setString(3, language);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER: BookType.EBOOK);
				books.add(book);
			}
		}catch (SQLException e) {
			throw new DAOException("Unable to retrieve data",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	public BookInfo searchBookInfo(int bookId, String language) throws DAOException {
		BookInfo book = new BookInfo();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement(SELECT_BOOK_QUERY);
			statement.setInt(1, bookId);
			statement.setString(2, language);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				book = new BookInfo();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER: BookType.EBOOK);
				book.setDescription(resultSet.getString(DESCRIPTION));
				book.setImageUrl(resultSet.getString(IMAGE_URL));
			}
		}catch (SQLException e) {
			throw new DAOException("Unable to retrieve data",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return book;
	}
	
	
}
