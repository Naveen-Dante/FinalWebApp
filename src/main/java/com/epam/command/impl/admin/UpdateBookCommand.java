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

	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "errMsg";
	private static final String SUCCESS_MESSAGE = "success_msg";
	private static final String REDIRECT = "/admin?command=update";
	private static final Logger LOGGER = Logger.getLogger(UpdateBookCommand.class);
	private static BookService service;
	
	public UpdateBookCommand(){
		service = BookServiceImpl.getInstance();
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(SUCCESS_MESSAGE) != null) {
			System.out.println("In success");
			String msg = (String) session.getAttribute(SUCCESS_MESSAGE);
			session.removeAttribute(SUCCESS_MESSAGE);
			request.setAttribute(SUCCESS_MESSAGE, msg);
			request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
		} else if (!isValidForm(request)) {
			System.out.println("In error");
			response.sendRedirect("/admin?command=login");
		}else{
			System.out.println("In process");
			BookInfo book = new BookInfo();
			book.setTitle(request.getParameter("title"));
			book.setAuthor(request.getParameter("author"));
			book.setImageUrl(request.getParameter("imageurl"));
			book.setDescription(request.getParameter("description"));
			book.setLanguage(request.getParameter("language"));
			book.setId(Integer.parseInt(request.getParameter("id").trim()));
			String errorMsg = null;
			boolean success = false;
			try {
				success = service.UpdateBook(book);
				System.out.println(success);
				if (!success) {
					errorMsg = "Cannot Add this Book.";
					request.setAttribute(ERROR, true);
					request.setAttribute(ERROR_MESSAGE, errorMsg);
					request.getRequestDispatcher("/admin?command=login").forward(request, response);
				} else {
					session.setAttribute("isSuccess", true);
					session.setAttribute(SUCCESS_MESSAGE, "Book Updated Successfully");
					System.out.println("Redirecting");
					response.sendRedirect(REDIRECT);
					
				}
			} catch (ServiceException e) {
				LOGGER.info("Unable to add new Book to DB.", e);
				e.printStackTrace();
			}
		}
	}

	private boolean isValidForm(HttpServletRequest request) {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String imageURL = request.getParameter("imageurl");
		String description = request.getParameter("description");
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
