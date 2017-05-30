package com.epam.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.Book;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;

public class TopBooksCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(TopBooksCommand.class);
	private static final String LANGUAGE = "language";
	private static final String DEFAULT_LANGUAGE = "en_US";
	private static BookService service;
	
	public TopBooksCommand(){
		service = BookServiceImpl.getInstance();
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LANGUAGE) != null ? (String) session.getAttribute(LANGUAGE)
				: DEFAULT_LANGUAGE;
		List<Book> books = null;
		try{
			books = service.getTopBooks(language);
			if(books != null){
				request.setAttribute("books", books);
			}
			request.getRequestDispatcher("WEB-INF/jsp/topBooks.jsp").forward(request, response);
		}catch (ServiceException e) {
			LOGGER.error("Can't retrieve top Books..");
		}
	}

}
