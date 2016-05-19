package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
 
public class LoginPanel extends JPanel {
    
	private JTextField nameField; //user name field
    private JPasswordField passField; //password field
    private JButton loginButton;
    private LoginListener listener; //action listener for "Log in" button
 
    //Returns user name inserted in nameField
    public String getName() {
        return nameField.getText();
    }
 
    //Returns password inserted in passField
    public String getPassword() {
        String password = "";
        char[] pass = passField.getPassword();
        for(int i=0; i<pass.length; i++) {
            password += pass[i];
        }
        return password;
    }
 
    //Authorization panel constructor
    public LoginPanel(LoginListener listener) {
        super();
        this.listener = listener;
        this.listener.setPanel(this);
        createComponents();
    }
 
    //Creation of log in form
    private void createComponents() {
    	//Container for all login form elements
    	JPanel loginPanel = new JPanel();
    	loginPanel.setLayout(new BorderLayout());
    	
    	JLabel loginLabel = new JLabel("Please log in to see company data");
    	loginPanel.add(loginLabel, BorderLayout.NORTH);
    	
    	JLabel name = new JLabel("Name: ");
        JLabel password = new JLabel("Password: ");
        nameField = new JTextField();
        passField = new JPasswordField();
        
        //Container for login form fields and labels
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        inputPanel.setLayout(new GridLayout(2, 2, 10, 5));
        inputPanel.add(name);
        inputPanel.add(nameField);
        inputPanel.add(password);
        inputPanel.add(passField);
        
        loginPanel.add(inputPanel, BorderLayout.CENTER);
        
        //"Log in" button
        loginButton = new JButton("Log in");
        loginButton.addActionListener(listener);
        loginPanel.add(loginButton, BorderLayout.SOUTH);
        
        loginPanel.setBorder(new EmptyBorder(230, 10, 0, 0));
        
        //Adding form panel to parent - LoginPanel 
        this.add(loginPanel);
    }
    
    /*
     * Login data validation -- temporary solution
     * */
    private static final String _name = "user";
    private static final String _password = "pass";
 
    public static boolean authenticate(String name, String password, JPanel loginPanel) {
        if(_name.equals(name) & _password.equals(password))
            return true;
        else
        	//Providing wrong login data toggles message dialog
        	JOptionPane.showMessageDialog(loginPanel, new JLabel("Wrong user name or password!"),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
    }
    /*
     * 
     * */
}