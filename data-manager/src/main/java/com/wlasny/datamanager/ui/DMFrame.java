package com.wlasny.datamanager.ui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;


/*
 * DMFrame is a container for components of the GUI
 * 
 */


public class DMFrame extends JFrame{
	
	
	
	
	
	public void init(){
		
		//super("Data manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setState(Frame.NORMAL);
		
		pack();
		
	}
	
}
