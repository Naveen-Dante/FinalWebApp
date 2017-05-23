package com.epam.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.AdminBook;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class GetBooksCommand implements Command {

	private static BookService bookService;
	private static final String ALL_BOOKS = "allBooks";
	private static final String TOTAL_BOOKS = "totalBooks";
	private static final String ADMIN_PAGE = "WEB-INF/jsp/admin.jsp";
	private static final Logger LOGGER = Logger.getLogger(GetBooksCommand.class);
	
	public GetBooksCommand(){
		bookService = BookServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute(ALL_BOOKS);
		List<AdminBook> books;
		try{
			int totalBooksPresent = bookService.getBooksCount();
			books = bookService.getAllBooks();
			session.setAttribute(ALL_BOOKS, books);
			session.setAttribute(TOTAL_BOOKS, totalBooksPresent);
			request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
		}catch (ServiceException e) {
			LOGGER.error("Cannot fetch the Books",e);
		}
	}

}
