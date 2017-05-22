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
		request.setAttribute("areFavs", true);
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LANGUAGE);
		try {
			if(session.getAttribute(BOOKS) == null){
				books = bookService.getAllBooks(language != null? language:"en_US");
				if(books != null){
					session.setAttribute(BOOKS, books);
				}
				response.sendRedirect("/");
			}
			else{
				request.getRequestDispatcher("/").forward(request, response);
			}
		} catch (ServiceException e) {
			LOGGER.error("Can't retrieve book details.");
		}
	}

}
