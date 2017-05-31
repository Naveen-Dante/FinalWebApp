package com.epam.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.service.LoginService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.LoginServiceImpl;

public class LoginCommand implements Command {

	private static final String HOME = "/";
	private static final String ADMIN = "/admin?command=login";
	private static final String REDIRECT = "/command?name=login";
	private static final String IS_LOGGED_IN = "isLoggedIn";
	private static final String USER = "user";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "error_message";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
	private static LoginService service;

	public LoginCommand() {
		service = LoginServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		String errorMsg = null;
		if (session.getAttribute(USER) != null) {
			User user = (User)session.getAttribute(USER);
			if(user.isAdmin()){
				response.sendRedirect(ADMIN);
			}
			else{
				request.getRequestDispatcher(HOME).forward(request, response);
			}
		} else {
			if (userName == null || password == null) {
				response.sendRedirect(HOME);
			} else {
				User user;
				try {
					user = service.getUser(userName, password);
					if (user == null) {
						errorMsg = "Invalid UserName or Password!";
						request.setAttribute(ERROR_MESSAGE, errorMsg);
						request.setAttribute(ERROR, true);
						request.getRequestDispatcher(HOME).forward(request, response);
					} else {
						session.setAttribute(USER, user);
						session.setAttribute(IS_LOGGED_IN, true);
						if (user.isAdmin()) {
							response.sendRedirect(ADMIN);
						} else
							response.sendRedirect(REDIRECT);
					}
				} catch (ServiceException e) {
					LOGGER.error("Unable to perform Operation.", e);
				}
			}
		}
	}
}
