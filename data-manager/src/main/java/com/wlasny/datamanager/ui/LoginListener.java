package com.wlasny.datamanager.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
 
public class LoginListener implements ActionListener {

    private final JFrame frame; //Main frame of Data Manager
    private LoginPanel loginPanel;
 
    public void setPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }
 
    public LoginListener(JFrame frame) {
        this.frame = frame;
    }
    
    //Definition of what happens when user clicks on the "Log in" button
    public void actionPerformed(ActionEvent event) {
        //Retrieve access data from text fields
    	String name = loginPanel.getName();
        String password = loginPanel.getPassword();
        if (LoginPanel.authenticate(name, password, loginPanel)) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	//create Data Manager panel if user provides correct data
                    JPanel dmPanel;
					try {
						dmPanel = new DMContentPanel();
						frame.getContentPane().removeAll();
	                    frame.add(dmPanel);
	                    frame.validate();
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                }
            });
        }
    }
}
