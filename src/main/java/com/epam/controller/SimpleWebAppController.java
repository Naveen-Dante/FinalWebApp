package com.epam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.command.CommandProvider;


/**
 * Servlet implementation class SimpleWebAppController
 */
public class SimpleWebAppController extends HttpServlet {

	private static final String ADMIN_COMMAND = "command";

	private static final String NAME = "name";

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
		String parameter = request.getParameter(NAME);
		String adminCommand = request.getParameter(ADMIN_COMMAND);
		if(adminCommand != null){
			PROVIDER.getAdminCommand(adminCommand).execute(request, response);
		}
		else{
			PROVIDER.getCommand(parameter).execute(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parameter = request.getParameter(NAME);
		String adminCommand = request.getParameter(ADMIN_COMMAND);
		if(adminCommand != null){
			PROVIDER.getAdminCommand(adminCommand).execute(request, response);
		}
		else{
			PROVIDER.getCommand(parameter).execute(request, response);
		}
		//PROVIDER.getCommand(request.getParameter(NAME)).execute(request, response);
	}

}
