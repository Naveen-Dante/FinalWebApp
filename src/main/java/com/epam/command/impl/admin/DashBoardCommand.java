package com.epam.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.Book;
import com.epam.domain.User;
import com.epam.service.BookService;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;
import com.epam.service.impl.UserServiceImpl;

public class DashBoardCommand implements Command {

	private static final String HOME = "/";
	private static final String TOP_BOOK = "topBook";
	private static final String TOTAL_USERS = "totalUsers";
	private static final String TOTAL_BOOKS = "totalBooks";
	private static final String ADMIN_PAGE = "WEB-INF/jsp/admin.jsp";
	private static final String USER = "user";
	private static UserService userService;
	private static BookService bookService;
	private static final Logger LOGGER = Logger.getLogger(DashBoardCommand.class);
	
	public DashBoardCommand() {
		userService = UserServiceImpl.getInstance();
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User admin = (User) session.getAttribute(USER);
		try {
			if (admin.isAdmin()) {
				int totalBooksPresent = bookService.getBooksCount();
				int totalUsersPresent = userService.getUserCount();
				Book book = bookService.getTopBook();
				session.setAttribute(TOTAL_BOOKS, totalBooksPresent);
				session.setAttribute(TOTAL_USERS, totalUsersPresent);
				session.setAttribute(TOP_BOOK, book);
				request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
			} else {
				response.sendRedirect(HOME);
			}
		} catch (ServiceException e) {
			LOGGER.error("Cannot Load Admin Portal",e);
		}
	}

}
