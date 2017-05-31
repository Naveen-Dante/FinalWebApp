package com.epam.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.domain.UserBook;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class DisplayBooksCommand implements Command {

	private static final String USER = "user";
	private static final String HOME_PAGE = "/";
	private static final String BOOKS_JSP = "WEB-INF/jsp/books.jsp";
	private static final String DEFAULT_LANGUAGE = "en_US";
	private static final String LANGUAGE = "language";
	private static BookService bookService;
	private static final Logger LOGGER = Logger.getLogger(DisplayBooksCommand.class);
	private static final String BOOKS = "books";

	public DisplayBooksCommand() {
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LANGUAGE) != null ? (String) session.getAttribute(LANGUAGE)
				: DEFAULT_LANGUAGE;
		User user = (User) session.getAttribute(USER);
		List<UserBook> books = null;
		try {
			if(user != null){
				books = bookService.getAllBooks(language,user.getUserName());
			}
			else{
				books = bookService.getAllBooks(language);
			}
			if (books != null) {
				request.setAttribute(BOOKS, books);
				request.getRequestDispatcher(BOOKS_JSP).forward(request, response);
			} else {
				request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			}
		} catch (ServiceException e) {
			LOGGER.error("Can't retrieve book details.",e);
		}
	}

}
