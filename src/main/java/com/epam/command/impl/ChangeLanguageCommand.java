/**
 * 
 */
package com.epam.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.command.Command;

/**
 * @author Naveen_Kumar
 *
 */
public class ChangeLanguageCommand implements Command {

	private static final String HOME = "/";
	private static final String PAGE_LANG = "lang";
	private static final String LANGUAGE = "language";

	/* (non-Javadoc)
	 * @see com.epam.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String language = request.getParameter(PAGE_LANG);
		session.setAttribute(LANGUAGE, language);
		request.getRequestDispatcher(HOME).forward(request, response);
	}

}
