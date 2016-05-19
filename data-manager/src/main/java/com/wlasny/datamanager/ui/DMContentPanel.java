package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wlasny.model.Company;
import com.wlasny.model.ManagerDAO;

public class DMContentPanel extends JPanel {

	public DMContentPanel() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		setLayout(new BorderLayout());
		
		
		JLabel title = new JLabel("Data manager");
		add(title, BorderLayout.NORTH);
		
		final JPanel content = new JPanel();
		content.setLayout(new GridLayout(5, 1));

		
		/*
		 * First section - list of all companies
		 * */
		JPanel companiesPanel = new JPanel();
		
		// Label for the section
		JLabel companiesLabel = new JLabel("Companies (select one to see matrials)");
		companiesPanel.add(companiesLabel);
		
		// Table columns definition
		String columnNames[] = { "Name", "CompanyID"};
		
		// Defining table model
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		// Retrieving data for the list
		final ManagerDAO dao = ManagerDAO.getInstance();
		List<Company> companies = dao.getCompanies();
		
		for (int i = 0; i < companies.size(); i++){
			   String companyName = companies.get(i).getCompanyName();
			   int companyID = companies.get(i).getCompanyID();

			   Object[] data = {companyName, companyID};

			   tableModel.addRow(data);

			}
		
		final JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            //System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
	            
	            content.remove(1);
	            content.add(new ProductsPanel(table.getValueAt(table.getSelectedRow(), 1).toString(), dao), 1);
	            content.validate();
	        }
	        
	    });
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		companiesPanel.add(scrollPane);
		
		content.add(companiesPanel,0);

		
		/*
		 * Second section - list of all products by company ID
		 * */
		JPanel productsPanel = new ProductsPanel();
		content.add(productsPanel,1);
		
		
		add( content, BorderLayout.CENTER );
		
		
		
		//Panel for buttons
		JPanel buttons = new JPanel();
		JButton button = new JButton("Test");
		buttons.add(button);
		add(buttons, BorderLayout.SOUTH);

	}
		
}
