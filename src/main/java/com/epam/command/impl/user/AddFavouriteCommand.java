package com.epam.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class AddFavouriteCommand implements Command {

	private static BookService service;
	private static final Logger LOGGER = Logger.getLogger(AddFavouriteCommand.class);
	
	public AddFavouriteCommand(){
		service = BookServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		int bookId = Integer.parseInt(request.getParameter("id"));
		boolean status = false;
		try{
			status = service.addFavouriteBook(bookId, user.getUserName());
			if(status){
				response.sendRedirect("/FinalWebApp/command?name=books");
			}
			else
				request.getRequestDispatcher("WEB_INF/jsp/books.jsp").forward(request, response);
		}catch (ServiceException e) {
			// TODO: handle exception
			LOGGER.error("Unable to create favourites....");
		}
		
	}

}
