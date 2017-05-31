package com.epam.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.BookInfo;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.BookServiceImpl;
import com.mysql.cj.core.util.StringUtils;

public class UpdateBookCommand implements Command {

	private static final String ADMIN_BOOKS_PAGE = "/admin?command=books";
	private static final String ADMIN_DASHBOARD = "/admin?command=login";
	private static final String ID = "id";
	private static final String LANGUAGE = "language";
	private static final String DESCRIPTION = "description";
	private static final String IMAGE_URL = "imageurl";
	private static final String AUTHOR = "author";
	private static final String TITLE = "title";
	private static final String ADMIN_PAGE = "WEB-INF/jsp/adminBooks.jsp";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "errMsg";
	private static final String SUCCESS_MESSAGE = "success_msg";
	private static final String REDIRECT = "/admin?command=update";
	private static final Logger LOGGER = Logger.getLogger(UpdateBookCommand.class);
	private static BookService service;

	public UpdateBookCommand() {
		service = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(SUCCESS_MESSAGE) != null) {
			
			String msg = (String) session.getAttribute(SUCCESS_MESSAGE);
			session.removeAttribute(SUCCESS_MESSAGE);
			request.setAttribute(SUCCESS_MESSAGE, msg);
			request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
			
		} else if (!isValidForm(request)) {
			
			response.sendRedirect(ADMIN_BOOKS_PAGE);
		} else {

			BookInfo book = new BookInfo();
			book.setTitle(request.getParameter(TITLE));
			book.setAuthor(request.getParameter(AUTHOR));
			book.setImageUrl(request.getParameter(IMAGE_URL));
			book.setDescription(request.getParameter(DESCRIPTION));
			book.setLanguage(request.getParameter(LANGUAGE));
			book.setId(Integer.parseInt(request.getParameter(ID).trim()));
			String errorMsg = null;
			boolean success = false;
			try {
				success = service.UpdateBook(book);
				if (!success) {
					errorMsg = "Cannot Update Book.";
					request.setAttribute(ERROR, true);
					request.setAttribute(ERROR_MESSAGE, errorMsg);
					request.getRequestDispatcher(ADMIN_DASHBOARD).forward(request, response);
				} else {
					session.setAttribute(SUCCESS_MESSAGE, "Book Updated Successfully");
					response.sendRedirect(REDIRECT);

				}
			} catch (ServiceException e) {
				LOGGER.info("Unable to update Book.", e);
				e.printStackTrace();
			}
		}
	}

	private boolean isValidForm(HttpServletRequest request) {
		String title = request.getParameter(TITLE);
		String author = request.getParameter(AUTHOR);
		String imageURL = request.getParameter(IMAGE_URL);
		String description = request.getParameter(DESCRIPTION);
		if (StringUtils.isNullOrEmpty(title) || StringUtils.isEmptyOrWhitespaceOnly(title)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(author) || StringUtils.isEmptyOrWhitespaceOnly(author)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(imageURL) || StringUtils.isEmptyOrWhitespaceOnly(imageURL)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(description) || StringUtils.isEmptyOrWhitespaceOnly(description)) {
			return false;
		}
		return true;
	}
}
