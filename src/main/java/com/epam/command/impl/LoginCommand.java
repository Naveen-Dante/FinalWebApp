package com.epam.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class LoginCommand implements Command{


	//private static final String BOOKS = "books";
	//private static final String WELCOME_PAGE = "WEB-INF/welcome.jsp";
	private static final String ADMIN_PAGE = "WEB-INF/admin.jsp";
	private static final String IS_LOGGED_IN = "isLoggedIn";
	private static final String USER = "user";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "error_message";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
	private static LoginService service;
	
	public LoginCommand(){
		service = LoginServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		String errorMsg = null;
		String language = (String) session.getAttribute("language");
		System.out.println(language);
		if(userName == null || password == null){
			response.sendRedirect("/");
		}
		else{
			User user;
			try{
				user = service.getUser(userName, password);
				if(user == null){
					errorMsg = "Invalid UserName or Password!";
					request.setAttribute(ERROR_MESSAGE, errorMsg);
					request.setAttribute(ERROR, true);
					request.getRequestDispatcher("/").forward(request, response);
				}
				else{
					//books = bookService.getBooks(user.getUserName(),language);
					//session.setAttribute(BOOKS, books);
					session.setAttribute(USER, user);
					session.setAttribute(IS_LOGGED_IN, true);
					System.out.println("redirecting user");
					if(user.isAdmin()){
						request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
					}
					else
						response.sendRedirect("/");
				}
			}catch (ServiceException e) {
				LOGGER.error("Unable to perform Operation.",e);
			}
		}
	}
}
