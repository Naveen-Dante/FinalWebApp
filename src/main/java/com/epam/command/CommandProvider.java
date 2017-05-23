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
import com.epam.command.impl.user.DisplayBooksCommand;
import com.epam.command.impl.user.FavouritesCommand;
import com.epam.command.impl.user.NewUserCommand;

public class CommandProvider {

	private static Map<String, Command> commandMap = new HashMap<String, Command>();
	private static Map<String, Command> adminCommandMap = new HashMap<String, Command>();
	
	public CommandProvider(){
		commandMap.put("login", new LoginCommand());
		commandMap.put("logout", new LogoutCommand());
		commandMap.put("new", new NewUserCommand());
		commandMap.put("books", new DisplayBooksCommand());
		commandMap.put("book", new BookCommand());
		commandMap.put("search", new SearchCommand());
		commandMap.put("changelanguage", new ChangeLanguageCommand());
		commandMap.put("favourites", new FavouritesCommand());
		commandMap.put(null, new DisplayBooksCommand());
		/*commandMap.put("get", new PageLoadCommand());
		commandMap.put("login", new LoginCommand());		
		*/
		adminCommandMap.put("login", new DashBoardCommand());
		adminCommandMap.put("new-book", new AddNewBookCommand());
		adminCommandMap.put("update", new UpdateBookCommand());
		adminCommandMap.put("book", new BookViewCommand());
		adminCommandMap.put("books", new GetBooksCommand());
		adminCommandMap.put("remove-book", new RemoveBookCommand());
		adminCommandMap.put("home", new DashBoardCommand());
		adminCommandMap.put("users", new UserCommand());
		adminCommandMap.put("logout", new LogoutCommand());
	}
	
	public Command getCommand(String command){
		return commandMap.get(command);
	}
	
	public Command getAdminCommand(String command){
		return adminCommandMap.get(command);
	}
}
