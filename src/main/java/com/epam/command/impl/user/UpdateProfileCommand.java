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
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.UserServiceImpl;
import com.mysql.cj.core.util.StringUtils;

public class UpdateProfileCommand implements Command {

	private static final String BASE_URL = "/command?";
	private static final String HOME_PAGE = "/";
	private static UserService service;
	private static final Logger LOGGER = Logger.getLogger(UpdateProfileCommand.class);

	public UpdateProfileCommand() {
		service = UserServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String requestUrl = generateURL(request.getParameter("queryUrl"));
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String phoneNumber = request.getParameter("phone");
		if (verify(firstName, lastName, phoneNumber)) {
			try {
				boolean status = service.updateUser(user.getUserName(), firstName, lastName, phoneNumber);
				if (status) {
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setPhoneNumber(new BigInteger(phoneNumber));
					session.setAttribute("user", user);
					System.out.println(requestUrl);
					response.sendRedirect(requestUrl);
				}
			} catch (ServiceException e) {
				LOGGER.error("Cannot update the user profile..", e);
			}

		}

	}

	private String generateURL(String parameter) {
		return StringUtils.isNullOrEmpty(parameter) || StringUtils.isEmptyOrWhitespaceOnly(parameter) ? HOME_PAGE
				: BASE_URL + parameter;
	}

	private boolean verify(String firstName, String lastName, String phoneNumber) {
		if (StringUtils.isNullOrEmpty(firstName) || StringUtils.isEmptyOrWhitespaceOnly(firstName)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(lastName) || StringUtils.isEmptyOrWhitespaceOnly(lastName)) {
			return false;
		}
		if (StringUtils.isNullOrEmpty(phoneNumber) || StringUtils.isEmptyOrWhitespaceOnly(phoneNumber)) {
			return false;
		}
		return true;
	}

}
