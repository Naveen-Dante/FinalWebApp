package com.epam.command.impl.user;

import java.io.IOException;

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

public class ChangePasswordCommand implements Command {

	private static UserService service;
	private static final String BASE_URL = "/FinalWebApp/command?";
	private static final String HOME_PAGE = "/";
	private static final Logger LOGGER = Logger.getLogger(ChangePasswordCommand.class);
	
	public ChangePasswordCommand(){
		service = UserServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("new-password");
		String requestUrl = generateURL(request.getParameter("queryUrl"));
		System.out.println(requestUrl);
		if(verify(user, password, newPassword)){
			try {
				boolean status = service.changePassword(user.getUserName(), password, newPassword);
				System.out.println(status);
				if (status) {
					response.sendRedirect(requestUrl);
				}
			} catch (ServiceException e) {
				LOGGER.error("Cannot update the user password..", e);
			}
		}
		
		
	}
	
	private String generateURL(String parameter) {
		return StringUtils.isNullOrEmpty(parameter) || StringUtils.isEmptyOrWhitespaceOnly(parameter) ? HOME_PAGE
				: BASE_URL + parameter;
	}

	private boolean verify(User user, String password, String newPassword) {
		if(user == null){
			return false;
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(password) || StringUtils.isNullOrEmpty(password)){
			return false;
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(newPassword) || StringUtils.isNullOrEmpty(newPassword)){
			return false;
		}
		return true;
	}

}
