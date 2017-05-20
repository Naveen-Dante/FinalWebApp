package com.epam.command.impl;

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

public class SearchCommand implements Command {

	private static final String SEARCH_JSP = "WEB-INF/jsp/search.jsp";
	private static final String ERROR = "error";
	private static final String SEARCH_RESULT = "searchResult";
	private static final String SEARCH_TEXT = "searchText";
	private static final String LANGUAGE = "language";
	private static final Logger LOGGER = Logger.getLogger(SearchCommand.class);
	private static BookService service;

	public SearchCommand() {
		service = BookServiceImpl.getInstance();
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(LANGUAGE);
		String searchText = request.getParameter(SEARCH_TEXT);
		try {
			List<Book> searchedBook = service.searchBook(searchText, language);
			if (searchedBook != null) {
				request.setAttribute(ERROR, false);
				request.setAttribute(SEARCH_RESULT, searchedBook);
			} else {
				request.setAttribute(ERROR, true);
			}
			request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
			// response.sendRedirect("searchPage");
		} catch (ServiceException e) {
			LOGGER.error("Service Exception", e);
		}

	}

}
