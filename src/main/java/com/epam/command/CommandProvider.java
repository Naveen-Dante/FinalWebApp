package com.epam.command;

import java.util.HashMap;
import java.util.Map;

import com.epam.command.impl.BookCommand;
import com.epam.command.impl.ChangeLanguageCommand;
import com.epam.command.impl.LoginCommand;
import com.epam.command.impl.LogoutCommand;
import com.epam.command.impl.SearchCommand;
import com.epam.command.impl.admin.AddNewBookCommand;
import com.epam.command.impl.admin.BookViewCommand;
import com.epam.command.impl.admin.DashBoardCommand;
import com.epam.command.impl.admin.GetBooksCommand;
import com.epam.command.impl.admin.RemoveBookCommand;
import com.epam.command.impl.admin.UpdateBookCommand;
import com.epam.command.impl.admin.UserCommand;
import com.epam.command.impl.user.AddFavouriteCommand;
import com.epam.command.impl.user.ChangePasswordCommand;
import com.epam.command.impl.user.DisplayBooksCommand;
import com.epam.command.impl.user.FavouritesCommand;
import com.epam.command.impl.user.NewUserCommand;
import com.epam.command.impl.user.UpdateProfileCommand;
import com.epam.command.impl.user.RemoveFavourite;
import com.epam.command.impl.user.TopBooksCommand;

public class CommandProvider {

	private static final String USERS = "users";
	private static final String HOME = "home";
	private static final String REMOVE_BOOK = "remove-book";
	private static final String NEW_BOOK = "new-book";
	private static final String TOP_BOOKS = "top";
	private static final String CHANGE_PASSWORD = "changepassword";
	private static final String UPDATE = "update";
	private static final String PROFILE = "profile";
	private static final String REMOVE_FROM_FAVS = "removefavs";
	private static final String ADD_TO_FAVS = "addfavs";
	private static final String FAVOURITES = "favourites";
	private static final String CHANGE_LANGUAGE = "changelanguage";
	private static final String SEARCH = "search";
	private static final String BOOK = "book";
	private static final String BOOKS = "books";
	private static final String NEW = "new";
	private static final String LOGOUT = "logout";
	private static final String LOGIN = "login";
	private static Map<String, Command> commandMap = new HashMap<String, Command>();
	private static Map<String, Command> adminCommandMap = new HashMap<String, Command>();
	
	public CommandProvider(){
		commandMap.put(LOGIN, new LoginCommand());
		commandMap.put(LOGOUT, new LogoutCommand());
		commandMap.put(NEW, new NewUserCommand());
		commandMap.put(BOOKS, new DisplayBooksCommand());
		commandMap.put(BOOK, new BookCommand());
		commandMap.put(SEARCH, new SearchCommand());
		commandMap.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
		commandMap.put(FAVOURITES, new FavouritesCommand());
		commandMap.put(ADD_TO_FAVS, new AddFavouriteCommand());
		commandMap.put(REMOVE_FROM_FAVS, new RemoveFavourite());
		commandMap.put(PROFILE, new UpdateProfileCommand());
		commandMap.put(UPDATE, new UpdateProfileCommand());
		commandMap.put(CHANGE_PASSWORD, new ChangePasswordCommand());
		commandMap.put(TOP_BOOKS, new TopBooksCommand());
		
		
		adminCommandMap.put(LOGIN, new DashBoardCommand());
		adminCommandMap.put(NEW_BOOK, new AddNewBookCommand());
		adminCommandMap.put(UPDATE, new UpdateBookCommand());
		adminCommandMap.put(BOOK, new BookViewCommand());
		adminCommandMap.put(BOOKS, new GetBooksCommand());
		adminCommandMap.put(REMOVE_BOOK, new RemoveBookCommand());
		adminCommandMap.put(HOME, new DashBoardCommand());
		adminCommandMap.put(USERS, new UserCommand());
		adminCommandMap.put(LOGOUT, new LogoutCommand());
	}
	
	public Command getCommand(String command){
		return commandMap.get(command);
	}
	
	public Command getAdminCommand(String command){
		return adminCommandMap.get(command);
	}
}
