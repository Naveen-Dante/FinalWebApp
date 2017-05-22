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

public class DisplayBooksCommand implements Command {

	private static final String DEFAULT_LANGUAGE = "en_US";
	private static final String BOOK_LANGUAGE = "book_language";
	private static final String LANGUAGE = "language";
	private static BookService bookService;
	private static final Logger LOGGER = Logger.getLogger(DisplayBooksCommand.class);
	private static final String BOOKS = "books";

	public DisplayBooksCommand() {
		bookService = BookServiceImpl.getInstance();
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books;
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LANGUAGE) != null?(String) session.getAttribute(LANGUAGE):DEFAULT_LANGUAGE;
		String bookLanguage = (String) session.getAttribute(BOOK_LANGUAGE) != null? (String) session.getAttribute(BOOK_LANGUAGE): language;
		System.out.println(language);
		System.out.println(bookLanguage);
		session.setAttribute(BOOK_LANGUAGE, bookLanguage);
		try {
			if(session.getAttribute(BOOKS) == null || !language.equalsIgnoreCase(bookLanguage)){
				books = bookService.getAllBooks(language);
				if(books != null){
					session.setAttribute(BOOKS, books);
				}
				session.setAttribute(BOOK_LANGUAGE, language);
				request.getRequestDispatcher("/").forward(request, response);
			}
			else{
				System.out.println("forwarding");
				request.getRequestDispatcher("/").forward(request, response);
			}
		} catch (ServiceException e) {
			LOGGER.error("Can't retrieve book details.");
		}
	}

}
