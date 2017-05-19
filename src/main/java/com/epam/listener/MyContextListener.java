package com.epam.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.service.ConnectionPoolService;
import com.epam.service.impl.ConnectionPoolServiceImpl;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 *
 */
public class MyContextListener implements ServletContextListener {

	
	private static final ConnectionPoolService SERVICE = ConnectionPoolServiceImpl.getInstance();
    /**
     * Default constructor. 
     */
    public MyContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	SERVICE.destroy();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         SERVICE.init();
    }
	
}
