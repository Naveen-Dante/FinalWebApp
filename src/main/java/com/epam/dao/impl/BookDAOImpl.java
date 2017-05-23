package com.epam.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.dao.BookDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.pool.ConnectionPool;
import com.epam.dao.utility.Utility;
import com.epam.domain.AdminBook;
import com.epam.domain.Book;
import com.epam.domain.BookInfo;
import com.epam.domain.BookType;
import com.epam.domain.Language;

public class BookDAOImpl implements BookDAO {

	private static final String LANGUAGE = "language";

	private static final String BOOK_COUNT = "count";

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

	private static final String SEARCH_QUERY = "select t.id,title,author,type from (SELECT * FROM book "
			+ "WHERE MATCH (book.title,book.author,book.description) "
			+ "AGAINST (?)) as t join user_has_book on t.id=user_has_book.book_id "
			+ "join user on user_has_book.user_id=user.id where username=? and language=?";

	private static final String SEARCH_BOOK_QUERY = "SELECT * FROM book "
			+ "WHERE MATCH (book.title,book.author,book.description) " + "AGAINST (?) and language=?";

	private static final String SELECT_ALL_QUERY = "select * from book where language=?";

	private static final String SELECT_ALL_BOOKS_QUERY = "SELECT * FROM book";

	private static final String SELECT_BOOK_QUERY = "select * from book where id=? and language=?";

	private static final String BOOKS_COUNT_QUERY = "SELECT count(*) AS count FROM book";

	private static final String MAX_ID_QUERY = "select max(id) as max from book";

	private static final String INSERT_BOOK = "INSERT INTO `gg4hyz6gpvflvqpc`.`book` "
			+ "(`id`, `language`, `title`, `author`, `type`, `description`, `imageurl`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_BOOK = "UPDATE `gg4hyz6gpvflvqpc`.`book` SET "
			+ "`title`=?, `author`=?, `description`=?, `imageurl`=? WHERE `id`=? and`language`=?";

	private static final String DELETE_BOOK_RELATION = "DELETE FROM `gg4hyz6gpvflvqpc`.`user_has_book` WHERE `book_id`=?";

	private static final String DELETE_BOOK = "DELETE FROM `gg4hyz6gpvflvqpc`.`book` WHERE `id`=? and `language`=?";

	private BookDAOImpl() {

	}

	public static BookDAOImpl getInstance() {
		if (instance == null) {
			synchronized (BookDAOImpl.class) {
				if (instance == null)
					instance = new BookDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public List<Book> getAllBooks(String language) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_QUERY);
			statement.setString(1, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to retrieve data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;

	}

	public List<Book> getBooks(String username, String language) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setString(1, username);
			statement.setString(2, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to retrieve data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	public List<Book> searchBooks(String searchText, String language) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SEARCH_BOOK_QUERY);
			statement.setString(1, searchText);
			statement.setString(2, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to retrieve data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	public BookInfo searchBookInfo(int bookId, String language) throws DAOException {
		BookInfo book = null;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_BOOK_QUERY);
			statement.setInt(1, bookId);
			statement.setString(2, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				book = new BookInfo();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				book.setDescription(resultSet.getString(DESCRIPTION));
				book.setImageUrl(resultSet.getString(IMAGE_URL));
				book.setLanguage(resultSet.getString(LANGUAGE));
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to retrieve data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return book;
	}

	@Override
	public int getBooksCount() throws DAOException {
		int count = -1;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(BOOKS_COUNT_QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(BOOK_COUNT);
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to retrieve data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return count;
	}

	@Override
	public List<AdminBook> getAllBooks() throws DAOException {
		List<AdminBook> books = new ArrayList<AdminBook>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_BOOKS_QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AdminBook book = new AdminBook();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setLanguage(
						resultSet.getString(LANGUAGE).equalsIgnoreCase("en_US") ? Language.ENGLISH : Language.ESPANOL);
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("Unable to retrieve data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	@Override
	public boolean addNewBook(BookInfo book) throws DAOException {
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		PreparedStatement insertStatement = null;
		boolean success = false;
		try {
			statement = connection.prepareStatement(MAX_ID_QUERY);
			int id = new Random().nextInt(100);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("max");
			}
			insertStatement = connection.prepareStatement(INSERT_BOOK);
			insertStatement.setInt(1, id + 1);
			insertStatement.setString(2, book.getLanguage().equalsIgnoreCase("english") ? "en_US" : "es_ES");
			insertStatement.setString(3, book.getTitle());
			insertStatement.setString(4, book.getAuthor());
			insertStatement.setString(5, book.getType() == BookType.EBOOK ? "ebook" : "paper");
			insertStatement.setString(6, book.getDescription());
			insertStatement.setString(7, book.getImageUrl());
			success = insertStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			throw new DAOException("Unable to Write data", e);
		} finally {
			Utility.closeStatement(statement);
			Utility.closeStatement(insertStatement);
			POOL.returnConnection(connection);
		}
		return success;
	}

	@Override
	public boolean updateBook(BookInfo book) throws DAOException {
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		boolean success = false;
		try {
			statement = connection.prepareStatement(UPDATE_BOOK);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(5, book.getId());
			statement.setString(3, book.getDescription());
			statement.setString(4, book.getImageUrl());
			statement.setString(6, book.getLanguage());
			success = statement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			throw new DAOException("Unable to Write data", e);
		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return success;
	}

	@Override
	public boolean removeBook(int bookId, String language) throws DAOException {
		Connection connection = null ;
		PreparedStatement statement = null;
		boolean success = false;
		try {
			if (deleteRelation(bookId)) {
				System.out.println("Calling procedure");
				connection = POOL.getConnection();
				statement = connection.prepareCall(DELETE_BOOK);
				statement.setInt(1, bookId);
				statement.setString(2, language);
				success = statement.execute();
				System.out.println(success);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Unable to Write data", e);

		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return success;
	}

	private boolean deleteRelation(int bookId) throws DAOException {
		boolean success;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
				System.out.println("Calling procedure");
				statement = connection.prepareCall(DELETE_BOOK_RELATION);
				statement.setInt(1, bookId);
				statement.executeUpdate();
				success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Unable to Write data", e);
		} finally {
			Utility.closeStatement(statement);
		}
		return success;
	}
}
