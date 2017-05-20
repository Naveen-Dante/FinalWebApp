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

public class DisplayBooksCommand implements Command {

	private static BookService bookService;
	private static final Logger LOGGER = Logger.getLogger(DisplayBooksCommand.class);

	public DisplayBooksCommand() {
		bookService = BookServiceImpl.getInstance();
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession(true);
		try {
			List<Book> books = bookService.getAllBooks("en_US");
			for(Book book: books){
				System.out.println(book.toString());
			}
		} catch (ServiceException e) {
			LOGGER.error("Can't retrieve book details.");
		}
	}

}
