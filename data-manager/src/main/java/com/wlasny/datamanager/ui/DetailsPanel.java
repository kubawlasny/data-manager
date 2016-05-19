package com.wlasny.datamanager.ui;

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
import com.wlasny.model.MaterialDetails;

public class DetailsPanel extends JPanel {

	private int ID;
	private ManagerDAO dao;

	public DetailsPanel() {

		JLabel detailsLabel = new JLabel(
				"Details (select a material to see the details");
		add(detailsLabel);

	}

	public DetailsPanel(String companyID, ManagerDAO dao) {

		this.ID = Integer.parseInt(companyID);
		this.dao = dao;
		addDetails();

	}

	private void addDetails() {

		// Label for the section
		JLabel detailsLabel = new JLabel(
				"Details of chosen material");
		add(detailsLabel);

		// Table columns definition
		String columnNames[] = { "Name", "Value" };

		// Defining table model
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {

			@Override
			public boolean isCellEditable(int row, int column) {
				
				if (column == 1 && row !=6){
					return true;
				} else {
				// all cells false
					return false;
				}
			}
		};

		// Retrieving data for the list
		List<MaterialDetails> details = dao.getDetails();
		
		MaterialDetails material = details.get((ID-1));
		// Adding rows to table model with values of given properties 
		Object[] name = { "Name", material.getName()};
		tableModel.addRow(name);
		Object[] description = { "Description", material.getDescription()};
		tableModel.addRow(description);
		Object[] notes = { "Notes", material.getName()};
		tableModel.addRow(notes);
		Object[] supplier = { "Supplier", material.getSupplier()};
		tableModel.addRow(supplier);
		Object[] price = { "Price", material.getPrice()};
		tableModel.addRow(price);
		Object[] currency = { "Currency", material.getCurrency()};
		tableModel.addRow(currency);
		Object[] id = { "ID", material.getId()};
		tableModel.addRow(id);
		
		// Creating table
		final JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						// System.out.println(table.getValueAt(table.getSelectedRow(),
						// 1).toString());
						/*content.add(
								new DetailsPanel(table.getValueAt(
										table.getSelectedRow(), 1).toString(),
										dao), 2);*/
					}
					

				});
		
		// Adding table to scrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

}
