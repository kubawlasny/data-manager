/**
 * AppFrame.java - main container of the GUI
 */

package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;



import javax.swing.JFrame;
import javax.swing.JPanel;


public class AppFrame extends JFrame {
	
	public AppFrame() {
		
		super("Data manager");
		
		//Frame properties	
		setPreferredSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		//Action listener for login handling
		LoginListener listener = new LoginListener(this);
		
		//First screen of the application - user authorization
		JPanel loginPanel = new LoginPanel(listener);
        add(loginPanel);

		
	}

}
