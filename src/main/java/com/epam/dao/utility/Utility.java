package com.epam.dao.utility;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Utility {

	private static final String LOG_CONFIG = "resource/ConfigLogger.properties";
	private static final Logger LOGGER = Logger.getLogger(Utility.class);
	
	public static void init() {
		PropertyConfigurator.configure(LOG_CONFIG);
	}
	
	public static void closeStatement(PreparedStatement statement){
		try {
			statement.close();
		} catch (SQLException e) {
			LOGGER.error("Cannot close the statement.",e);
		}
	}
}
