package com.epam.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.command.impl.BookCommand;
import com.epam.domain.BookInfo;
import com.epam.domain.Language;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class BookViewCommand implements Command {

	private static final String LANGUAGE = "language";
	private static final String ID = "id";
	private static final String BOOK_JSP = "WEB-INF/jsp/updateBook.jsp";
	private static final String BOOK_ERROR = "book_error";
	private static final String BOOK = "book";
	private static final Logger LOGGER = Logger.getLogger(BookCommand.class);
	private static BookService service;

	public BookViewCommand() {
		service = BookServiceImpl.getInstance();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String language = request.getParameter(LANGUAGE);
		int bookId = Integer.parseInt(request.getParameter(ID));
		try {
			BookInfo searchedBook = service.getBookInfo(bookId, language.equalsIgnoreCase("english")? "en_US": "es_ES" );
			if(searchedBook != null){
				request.setAttribute(BOOK, searchedBook);
				request.setAttribute(BOOK_ERROR, false);
			}
			else{
				request.setAttribute(BOOK_ERROR, true);
			}
			request.getRequestDispatcher(BOOK_JSP).forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("Service Exception", e);
		}
	}

}
