package com.epam.command.impl.user;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.service.NewUserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.NewUserServiceImpl;
import com.mysql.cj.core.util.StringUtils;

public class NewUserCommand implements Command {
	private static final String HOME = "/";
	private static final String REDIRECT = "/command?name=new";
	private static final String USER_SUCCESSFULLY_CREATED = "User Successfully Created.";
	private static final String SUCCESS_MESSAGE = "success_message";
	private static final String IS_LOGGED_IN = "isLoggedIn";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE = "error_message";
	private static final String ERROR = "error";
	private static final String USER_NAME_NOT_AVAILABLE_PLEASE_TRY_AGAIN = "UserName Not Available.. Please try Again..";
	private static final String TEXT_HTML = "text/html";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String USER_NAME = "user-name";
	private static final String LAST_NAME = "last-name";
	private static final String FIRST_NAME = "first-name";
	private static NewUserService service;
	private static final Logger LOGGER = Logger.getLogger(NewUserCommand.class);

	public NewUserCommand() {
		service = NewUserServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!isCompleteForm(request)) {
			response.sendRedirect(HOME);
		} else {
			HttpSession session = request.getSession(true);
			if (session.getAttribute(SUCCESS_MESSAGE) != null) {
				String msg = (String) session.getAttribute(SUCCESS_MESSAGE);
				session.removeAttribute(SUCCESS_MESSAGE);
				request.setAttribute(SUCCESS_MESSAGE, msg);
				request.getRequestDispatcher(HOME).forward(request, response);
			} else {

				User user = new User();
				user.setFirstName(request.getParameter(FIRST_NAME));
				user.setLastName(request.getParameter(LAST_NAME));
				user.setUserName(request.getParameter(USER_NAME));
				user.setPhoneNumber(new BigInteger(request.getParameter(PHONE)));
				user.setType(false);
				user.setEmail(request.getParameter(EMAIL));
				String password = request.getParameter(PASSWORD);
				boolean success = false;
				String errorMsg = null;
				response.setContentType(TEXT_HTML);
				if (!user.isValid() || password == null) {
					response.sendRedirect(HOME);
				} else {
					try {
						success = service.addNewUser(user, password);
						if (!success) {
							errorMsg = USER_NAME_NOT_AVAILABLE_PLEASE_TRY_AGAIN;
							request.setAttribute(ERROR, true);
							request.setAttribute(ERROR_MESSAGE, errorMsg);
							request.getRequestDispatcher(HOME).forward(request, response);
						} else {
							session.setAttribute(USER, user);
							session.setAttribute(IS_LOGGED_IN, true);
							session.setAttribute(SUCCESS_MESSAGE, USER_SUCCESSFULLY_CREATED);
							response.sendRedirect(REDIRECT);
						}
					} catch (ServiceException e) {
						errorMsg = "Sorry but we can not add the User: " + user.getFirstName() + " "
								+ user.getLastName() + ", due to technical difficulties. Please try later.";
						LOGGER.info("Unable to add new user to DB.", e);
					}
				}
			}
		}
	}

	private boolean isCompleteForm(HttpServletRequest request) {
		String email = request.getParameter(EMAIL);
		String first = request.getParameter(FIRST_NAME);
		String last = request.getParameter(LAST_NAME);
		String phone = request.getParameter(PHONE);
		String pass = request.getParameter(PASSWORD);
		String user = request.getParameter(USER_NAME);

		if (StringUtils.isNullOrEmpty(email) || StringUtils.isEmptyOrWhitespaceOnly(email)) {
			return false;
		} else if (StringUtils.isNullOrEmpty(first) || StringUtils.isEmptyOrWhitespaceOnly(first)) {
			return false;
		} else if (StringUtils.isNullOrEmpty(last) || StringUtils.isEmptyOrWhitespaceOnly(last)) {
			return false;
		} else if (StringUtils.isNullOrEmpty(phone) || StringUtils.isEmptyOrWhitespaceOnly(phone)) {
			return false;
		} else if (StringUtils.isNullOrEmpty(pass) || StringUtils.isEmptyOrWhitespaceOnly(pass)) {
			return false;
		} else if (StringUtils.isNullOrEmpty(user) || StringUtils.isEmptyOrWhitespaceOnly(user)) {
			return false;
		}
		return true;
	}
}
