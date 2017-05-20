package com.epam.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.command.CommandProvider;


/**
 * Servlet implementation class SimpleWebAppController
 */
public class SimpleWebAppController extends HttpServlet {
	private static final String REGEX = "=";

	private static final String BOOK_COMMAND = "book";

	private static final long serialVersionUID = 1L;
	
	private static final CommandProvider PROVIDER = new CommandProvider();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleWebAppController() {
		
	}

	@Override
	public void init() throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("name"));
		PROVIDER.getCommand(request.getParameter("name")).execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("name"));
		PROVIDER.getCommand(request.getParameter("name")).execute(request, response);
	}

}
