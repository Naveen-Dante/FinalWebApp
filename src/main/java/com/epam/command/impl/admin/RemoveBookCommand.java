package com.epam.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.command.impl.BookCommand;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class RemoveBookCommand implements Command {

	private static final String ERROR_MSG = "Cannot Delete Book At this Time";
	private static final String BOOK_DELETED = "Book Deleted!!!!";
	private static final String SUCCESS_MSG = "success_msg";
	private static final String LANGUAGE = "language";
	private static final String ID = "id";
	private static final String BOOK_ERROR = "book_error";
	private static final Logger LOGGER = Logger.getLogger(BookCommand.class);
	private static BookService service;

	public RemoveBookCommand(){
		service = BookServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String language = request.getParameter(LANGUAGE);
		int bookId = Integer.parseInt(request.getParameter(ID));
		boolean success = false;
		try {
			success = service.removeBook(bookId, language);
			if (success) {
				request.setAttribute(SUCCESS_MSG, BOOK_DELETED);
			} else {
				request.setAttribute(BOOK_ERROR, ERROR_MSG);
			}
			response.sendRedirect("/admin?command=home");
		} catch (ServiceException e) {
			e.printStackTrace();
			LOGGER.error("Service Exception", e);
		}
	}
}
