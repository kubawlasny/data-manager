package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.wlasny.model.Company;
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
		
		// Label for the section
		JLabel materialsLabel = new JLabel("Materials of the chosen company");
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
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            //System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
	            
	            content.remove(2);
	            content.add(new DetailsPanel(table.getValueAt(table.getSelectedRow(), 1).toString(), dao), 2);
	            content.validate();
	        }
	        
	    });
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
