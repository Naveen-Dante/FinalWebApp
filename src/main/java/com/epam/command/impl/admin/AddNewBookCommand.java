package com.epam.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.command.impl.BookCommand;
import com.epam.domain.BookInfo;
import com.epam.domain.BookType;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;
import com.mysql.cj.core.util.StringUtils;

public class AddNewBookCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(BookCommand.class);
	private static final String SUCCESS_MESSAGE = "Added new Book Successfully..";
	private static final String ERROR = null;
	private static final String ERROR_MESSAGE = null;
	private static final String REDIRECT = "/admin?command=new-book";
	private static BookService service;

	public AddNewBookCommand() {
		service = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		if (session.getAttribute(SUCCESS_MESSAGE) != null) {
			String msg = (String) session.getAttribute(SUCCESS_MESSAGE);
			session.removeAttribute(SUCCESS_MESSAGE);
			request.setAttribute(SUCCESS_MESSAGE, msg);
			request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
		} else if (!isCompleteForm(request)) {
			response.sendRedirect("/admin?command=login");
		} else {
			BookInfo book = new BookInfo();
			book.setTitle(request.getParameter("title"));
			book.setAuthor(request.getParameter("author"));
			book.setType(request.getParameter("opttype").equalsIgnoreCase("paper") ? BookType.PAPER : BookType.EBOOK);
			book.setImageUrl(request.getParameter("imageurl"));
			book.setDescription(request.getParameter("description"));
			book.setLanguage(request.getParameter("select-language"));
			String errorMsg = null;
			boolean success = false;
			try {
				success = service.addNewBook(book);
				if (!success) {
					errorMsg = "Cannot Add this Book.";
					request.setAttribute(ERROR, true);
					request.setAttribute(ERROR_MESSAGE, errorMsg);
					request.getRequestDispatcher("/admin?command=login").forward(request, response);
				} else {
					session.setAttribute("isSuccess", true);
					session.setAttribute(SUCCESS_MESSAGE, "Book Added Successfully");
					response.sendRedirect(REDIRECT);
				}
			} catch (ServiceException e) {
				LOGGER.info("Unable to add new Book to DB.", e);
				e.printStackTrace();
			}
		}
	}

	private boolean isCompleteForm(HttpServletRequest request) {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String imageURL = request.getParameter("imageurl");
		String language = request.getParameter("select-language");
		String description = request.getParameter("description");
		String type = request.getParameter("opttype");
		if (StringUtils.isNullOrEmpty(title) || StringUtils.isEmptyOrWhitespaceOnly(title)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(author) || StringUtils.isEmptyOrWhitespaceOnly(author)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(imageURL) || StringUtils.isEmptyOrWhitespaceOnly(imageURL)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(language) || StringUtils.isEmptyOrWhitespaceOnly(language)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(type) || StringUtils.isEmptyOrWhitespaceOnly(type)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(description) || StringUtils.isEmptyOrWhitespaceOnly(description)) {
			return false;
		}
		return true;
	}

}
