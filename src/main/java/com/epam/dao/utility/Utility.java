package com.epam.dao.utility;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class Utility {

	
	private static final Logger LOGGER = Logger.getLogger(Utility.class);
	
	public static void init() {
		
	}
	
	public static void closeStatement(PreparedStatement statement){
		try {
			statement.close();
		} catch (SQLException e) {
			LOGGER.error("Cannot close the statement.",e);
		}
	}
}
