package com.epam.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.command.Command;
import com.epam.domain.AdminBook;
import com.epam.domain.Book;
import com.epam.domain.User;
import com.epam.service.BookService;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;
import com.epam.service.impl.UserServiceImpl;

public class DashBoardCommand implements Command {

	private static final String ADMIN_PAGE = "WEB-INF/jsp/admin.jsp";
	private static final String USER = "user";
	private static UserService userService;
	private static BookService bookService;

	public DashBoardCommand() {
		userService = UserServiceImpl.getInstance();
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User admin = (User) session.getAttribute(USER);
		int totalBooks = session.getAttribute("totalBooks") == null? -1:(int)session.getAttribute("totalBooks");
		int totalUsers = session.getAttribute("totalUsers") == null? -1:(int)session.getAttribute("totalUsers");
		try {
			if (admin.isAdmin()) {
				List<AdminBook> books;
				List<User> users;
				int totalBooksPresent = bookService.getBooksCount();
				if(totalBooks != totalBooksPresent || session.getAttribute("allBooks") == null){
					books = bookService.getAllBooks();
					session.setAttribute("allBooks", books);
				}
				int totalUsersPresent = userService.getUserCount();
				if(totalUsers != totalUsersPresent || session.getAttribute("allUsers") == null){
					users = userService.getUsers();
					session.setAttribute("allUsers", users);
				}
				session.setAttribute("totalBooks", totalBooksPresent);
				session.setAttribute("totalUsers", totalUsersPresent);
				request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
			} else {
				response.sendRedirect("/");
			}
		} catch (ServiceException e) {
			
		}
	}

}
