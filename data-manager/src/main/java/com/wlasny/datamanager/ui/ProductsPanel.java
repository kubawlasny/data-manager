package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.wlasny.model.Company;
import com.wlasny.model.ManagerDAO;
import com.wlasny.model.Material;

public class ProductsPanel extends JPanel {
	
	private int companyID;
	private ManagerDAO dao;
	
	public ProductsPanel( ){
		
		JLabel title = new JLabel("Materials");
		add(title);
		
	}
	
	public ProductsPanel(String companyID, ManagerDAO dao) {
		
		this.companyID = Integer.parseInt(companyID);
		this.dao = dao;
		addProducts();
		
	}

	private void addProducts() {
		
		// Label for the section
		JLabel materialsLabel = new JLabel("Materials (select one to see details)");
		add(materialsLabel);
	
		// Table columns definition
		String columnNames[] = { "Name", "ID", "CompanyID"};
		
		// Defining table model
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		// Retrieving data for the list
		List<Material> materials = dao.getMaterials();
		
		for (int i = 0; i < materials.size(); i++){
			if (materials.get(i).getCompanyID()==companyID) {
			   String materialName = materials.get(i).getName();
			   int ID = materials.get(i).getID();
			   int companyID = materials.get(i).getCompanyID();

			   Object[] data = {materialName, ID, companyID};

			   tableModel.addRow(data);
			}


		}
		
		final JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
