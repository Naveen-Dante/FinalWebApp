package com.epam.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.UserServiceImpl;

public class UserCommand implements Command {

	private static final String ALL_USERS = "allUser";
	private static final String TOTAL_USERS = "totalUsers";
	private static final String ADMIN_PAGE = "WEB-INF/jsp/user.jsp";
	private static final String USER = "user";
	private static UserService userService;
	private static final Logger LOGGER = Logger.getLogger(DashBoardCommand.class);

	public UserCommand() {
		userService = UserServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User admin = (User) session.getAttribute(USER);
		try {
			if (admin.isAdmin()) {
				List<User> users;
				int totalUsersPresent = userService.getUserCount();
				users = userService.getUsers();
				request.setAttribute(ALL_USERS, users);
				for (User user : users) {
					System.out.println(user.toString());
				}
				session.setAttribute(TOTAL_USERS, totalUsersPresent);
				request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
			} else {
				response.sendRedirect("/");
			}
		} catch (ServiceException e) {
			LOGGER.error("Cannot Load Admin Portal", e);
		}
	}

}
