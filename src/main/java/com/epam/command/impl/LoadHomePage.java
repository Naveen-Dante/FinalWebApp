package com.epam.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;

public class LoadHomePage implements Command {

	private static final String INDEX_JSP = "WEB-INF/jsp/index.jsp";
	private static final String ADMIN_JSP = "WEB-INF/jsp/admin.jsp";
	private static final String USER = "user";
	private static final Logger LOGGER = Logger.getLogger(LoadHomePage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In home page..");
		LOGGER.info("In the HomePage");
		HttpSession session = request.getSession(true);
		if(session.getAttribute(USER) != null){
			User user = (User) session.getAttribute(USER);
			if(user.isAdmin()){
				request.getRequestDispatcher(ADMIN_JSP).forward(request, response);
			}
			else{
				request.getRequestDispatcher(INDEX_JSP).forward(request, response);
			}
		}
		else{
			request.getRequestDispatcher(INDEX_JSP).forward(request, response);
		}
	}

}
