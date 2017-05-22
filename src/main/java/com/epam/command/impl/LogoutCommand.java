package com.epam.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.command.Command;


public class LogoutCommand implements Command {

	private static final String IS_LOGGED_IN = "isLoggedIn";
	private static final String USER = "user";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute(USER);
		session.removeAttribute(IS_LOGGED_IN);
		response.sendRedirect("/");
	}

}
