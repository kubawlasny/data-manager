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
 
public class LoginPanel extends JPanel {
    private JTextField nameField; //user name field
    private JPasswordField passField; //password field
    private JButton loginButton;
    private LoginListener listener; //action listener for log in button
 
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
        JLabel name = new JLabel("Name: ");
        JLabel password = new JLabel("Password: ");
        nameField = new JTextField();
        passField = new JPasswordField();
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(name);
        inputPanel.add(nameField);
        inputPanel.add(password);
        inputPanel.add(passField);
        
        loginButton = new JButton("Log in");
        loginButton.addActionListener(listener);
 
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);
        parentPanel.add(loginButton, BorderLayout.SOUTH);
        
        //Adding form panel to parent - LoginPanel 
        this.add(parentPanel);
    }
    
    /*
     * Validation data -- temporary solution
     * */
    private static final String _name = "user";
    private static final String _password = "pass";
 
    public static boolean authenticate(String name, String password, JPanel loginPanel) {
        if(_name.equals(name) & _password.equals(password))
            return true;
        else
        	
        	JOptionPane.showMessageDialog(loginPanel, new JLabel("Wrong user name or password!"),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
    }
    /*
     * 
     * */
}