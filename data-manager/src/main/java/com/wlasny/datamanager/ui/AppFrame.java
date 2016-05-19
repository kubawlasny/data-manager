package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;



import javax.swing.JFrame;
import javax.swing.JPanel;


public class AppFrame extends JFrame {
	public AppFrame() {
		// TODO: move to spring?
		super("Data manager");

		setLayout(new BorderLayout());
		
		setPreferredSize(new Dimension(600, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		LoginListener listener = new LoginListener(this);
		JPanel loginPanel = new LoginPanel(listener);
        add(loginPanel, BorderLayout.CENTER);

		
	}

}
