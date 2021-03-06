package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.epam.domain.UserBook;

public class BookDAOImpl implements BookDAO {

	private static final String USERS = "users";

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

	private static final String DELETE_BOOK = "DELETE FROM `gg4hyz6gpvflvqpc`.`book` WHERE `id`=?";
	
	public static final String ALL_BOOK = "select group_concat(user.username) as 'users', book.* from book "+
			"left outer join user_has_book on book.id = user_has_book.book_id "
			+ "left outer join user on user.id = user_has_book.user_id "+
			"group by book_id having book.language = ?";
	
	private static final String ADD_FAVOURITE = "insert into user_has_book values"
				+ "((select user.id from user where username=?),?)";

	private static final String FETCH_FAVOURITES = "select book.* from book "
			+ "join user_has_book on book.id = user_has_book.book_id "
			+ "join user on user.id = user_has_book.user_id "
			+ "where user.username = ? and language = ?";

	private static final String REMOVE_FAVOURITE = "DELETE FROM `gg4hyz6gpvflvqpc`.`user_has_book` WHERE "
			+ "`user_id`=(select user.id from user where username=?) and`book_id`=?;";
	
	private static final String MOST_BOOKMARKED = "select * , count(user_has_book.book_id) as cnt from book "
			+ "left join user_has_book on book.id = user_has_book.book_id "
			+ "group by user_has_book.book_id "
			+ "having cnt > 0 and language = ? "
			+ "order by cnt desc limit 20";
	
	private static final String MOST_BOOKMARKED_BOOK = "select * from book "
			+ "where book.id = ( select book_id from user_has_book "
			+ "group by user_has_book.book_id "
			+ "order by count(*) desc limit 1)";

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
	public List<UserBook> getAllBooks(String language) throws DAOException {
		List<UserBook> books = new ArrayList<UserBook>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_QUERY);
			statement.setString(1, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserBook book = new UserBook();
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
		Connection connection = null;
		PreparedStatement statement = null;
		boolean success = false;
		try {
			System.out.println("Calling procedure");
			connection = POOL.getConnection();
			statement = connection.prepareCall(DELETE_BOOK);
			statement.setInt(1, bookId);
			success = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Unable to Write data", e);

		} finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return success;
	}

	@Override
	public List<UserBook> getAllBooks(String language, String userName) throws DAOException {
		List<UserBook> books = new ArrayList<UserBook>();
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(ALL_BOOK);
			statement.setString(1, language);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserBook book = new UserBook();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				String users = resultSet.getString(USERS);
				if(users != null){
					book.setFavourite(Arrays.asList(users.split(",")).contains(userName));
				}
				else
					book.setFavourite(false);
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
	public boolean addFavouriteBook(int bookId, String userName) throws DAOException {
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		boolean success = false;
		try {
			statement = connection.prepareStatement(ADD_FAVOURITE);
			statement.setString(1, userName);
			statement.setInt(2, bookId);
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
	public List<Book> getFavourites(String language, String userName) throws DAOException {
		List<Book> books = null;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			books = new ArrayList<>();
			statement = connection.prepareStatement(FETCH_FAVOURITES);
			statement.setString(1, userName);
			statement.setString(2, language);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				books.add(book);
			}
		}catch (SQLException e) {
			throw new DAOException("Can't get the details of the books.",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	@Override
	public boolean removeFavouriteBook(int bookId, String userName) throws DAOException {
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		boolean success = false;
		try {
			statement = connection.prepareStatement(REMOVE_FAVOURITE);
			statement.setString(1, userName);
			statement.setInt(2, bookId);
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
	public List<Book> getTopBooks(String language) throws DAOException {
		List<Book> books = null;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement(MOST_BOOKMARKED);
			statement.setString(1, language);
			ResultSet resultSet = statement.executeQuery();
			books = resultSet == null? null: new ArrayList<>();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
				books.add(book);
			}
		}catch (SQLException e) {
			throw new DAOException("Can't get the details of the books.",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return books;
	}

	@Override
	public Book getTopBooks() throws DAOException {
		Book book = null;
		Connection connection = POOL.getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement(MOST_BOOKMARKED_BOOK);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setTitle(resultSet.getString(BOOK_TITLE));
				book.setAuthor(resultSet.getString(BOOK_AUTHOR));
				book.setType(resultSet.getString(TYPE).equalsIgnoreCase(PAPER_BOUND) ? BookType.PAPER : BookType.EBOOK);
			}
		}catch (SQLException e) {
			throw new DAOException("Can't get the details of the books.",e);
		}finally {
			Utility.closeStatement(statement);
			POOL.returnConnection(connection);
		}
		return book;
	}
	
	
	
}
