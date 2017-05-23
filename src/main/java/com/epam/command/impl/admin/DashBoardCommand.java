package com.epam.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.command.Command;
import com.epam.domain.User;

public class DashBoardCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User admin = (User) session.getAttribute("user");
		if(admin.isAdmin()){
			request.getRequestDispatcher("WEB_INF/jsp/admin.jsp").forward(request, response);
		}
		else{
			response.sendRedirect("/");
		}
	}
	
}
