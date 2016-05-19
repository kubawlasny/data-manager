/**
 * App.java - starter class for the Data Manager application
 */

package com.wlasny.datamanager;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wlasny.datamanager.ui.AppFrame;
import com.wlasny.model.ManagerDAO;

public class App 
{
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, MalformedURLException, IOException
    {
    	//Spring application context
    	final ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
    	
    	//DAO initialization - initial data download from REST web service
    	ManagerDAO dao = (ManagerDAO) context.getBean("managerdao");
    	
    	//Frame initialization
    	//Ensuring against multiple threads
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppFrame();
            	//JFrame printer = (JFrame) context.getBean("appframe");
            }
        });
    	
    	
    	
    
    }
}
