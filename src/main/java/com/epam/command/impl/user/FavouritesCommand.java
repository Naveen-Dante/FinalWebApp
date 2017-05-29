package com.epam.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.Book;
import com.epam.domain.User;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class FavouritesCommand implements Command {

	private static final String LANGUAGE = "language";
	private static BookService bookService;
	private static final Logger LOGGER = Logger.getLogger(DisplayBooksCommand.class);
	private static final String BOOKS = "books";

	public FavouritesCommand() {
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> books;
		System.out.println("In favs");
		HttpSession session = request.getSession(false);
		String language = (String) session.getAttribute(LANGUAGE);
		User user = (User) session.getAttribute("user");
		try {
			if (user != null) {
				books = bookService.getFavourites(language != null ? language : "en_US", user.getUserName());
				if (books != null) {
					request.setAttribute(BOOKS, books);
					request.getRequestDispatcher("WEB-INF/jsp/favourite.jsp").forward(request, response);
				}
			}
		} catch (ServiceException e) {
			LOGGER.error("Can't retrieve book details.");
		}
	}

}
