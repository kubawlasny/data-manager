/**
 * MaterialsPanel.java - container for materials list of given company
 */
package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.wlasny.model.ManagerDAO;
import com.wlasny.model.Material;

public class MaterialsPanel extends JPanel {
	
	private int companyID;
	private ManagerDAO dao;
	private JPanel content;
	
	public MaterialsPanel( ){
		
		JLabel title = new JLabel("Materials (select a company to see the list)");
		add(title);
		
	}
	
	public MaterialsPanel(String companyID, ManagerDAO dao, JPanel content) {
		
		this.companyID = Integer.parseInt(companyID);
		this.dao = dao;
		this.content = content;
		addMaterials();
		
	}

	private void addMaterials() {
		
		setLayout(new BorderLayout());
		
		// Label for the section
		JLabel materialsLabel = new JLabel("Materials of chosen company");
		materialsLabel.setHorizontalAlignment(JLabel.CENTER);
		add(materialsLabel, BorderLayout.NORTH);
	
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
		
		// Action listener for selection of a material
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            
	            content.remove(2);
	            // Material ID is passed as a parameter 
	            content.add(new DetailsPanel(table.getValueAt(table.getSelectedRow(), 1).toString(), dao, content), 2);
	            content.validate();
	        }
	        
	    });
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);
	}
}
