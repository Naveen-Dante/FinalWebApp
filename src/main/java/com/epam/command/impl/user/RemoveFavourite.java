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

public class RemoveFavourite implements Command {

	private static BookService service;
	private static volatile Logger LOGGER = Logger.getLogger(RemoveFavourite.class);
	
	public RemoveFavourite(){
		service = BookServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int bookId = Integer.parseInt(request.getParameter("id").trim());
		boolean status = false;
		try{
			System.out.println(bookId);
			System.out.println(user.getUserName());
			status = service.removeFavouriteBook(bookId, user.getUserName());
			if(status){
				
			}
			response.sendRedirect("/command?name=favourites");
			
		}catch (ServiceException e) {
			// TODO: handle exception
			LOGGER.error("Unable to create favourites....");
		}
		
	}

}
