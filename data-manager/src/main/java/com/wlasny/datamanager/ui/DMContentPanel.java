/**
 * DMContentPanel.java - container for all the GUI elements + handling user actions
 */
package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wlasny.model.Company;
import com.wlasny.model.ManagerDAO;

public class DMContentPanel extends JPanel {

	public DMContentPanel() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		setLayout(new BorderLayout());
		
		//Container for three main sections of the screen
		final JPanel content = new JPanel();
		content.setLayout(new GridLayout(3, 1));
		
		/*
		 * First section - list of all companies
		 * */
		
		JPanel companiesPanel = new JPanel();
		companiesPanel.setLayout(new BorderLayout());
		
		// Label for the section
		JLabel companiesLabel = new JLabel("Companies (select one to see materials)");
		companiesLabel.setHorizontalAlignment(JLabel.CENTER);
		companiesPanel.add(companiesLabel, BorderLayout.NORTH);
		
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
		
		// Creating table for Companies
		final JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		
		// Action listener for selection of a Company
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	// Removing previous version of material list panel 
	            content.remove(1);
	            // Adding new one according to the chosen row of the table, companyID passed as a parameter
	            content.add(new MaterialsPanel(table.getValueAt(table.getSelectedRow(), 1).toString(), dao, content), 1);
	            content.remove(2);
	            content.add(new DetailsPanel(),2);
	            content.validate();
	        }
	        
	    });
		
		// Adding table to scrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		companiesPanel.add(scrollPane, BorderLayout.CENTER);
		
		content.add(companiesPanel,0);

		
		/*
		 * Second section - list of all materials by company ID
		 * */
		JPanel materialsPanel = new MaterialsPanel();
		content.add(materialsPanel,1);
		
		
		/*
		 * Third section - details of chosen material
		 * */
		JPanel detailsPanel = new DetailsPanel();
		content.add(detailsPanel,2);
		
		// Adding content to parent panel
		add(content, BorderLayout.CENTER );

	}
		
}
