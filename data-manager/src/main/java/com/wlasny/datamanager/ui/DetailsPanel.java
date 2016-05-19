/**
 * DetailsPanel.java - container for material details data
 */
package com.wlasny.datamanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wlasny.model.ManagerDAO;
import com.wlasny.model.MaterialDetails;

public class DetailsPanel extends JPanel {

	private int ID;
	private ManagerDAO dao;
	private JPanel content;

	public DetailsPanel() {

		JLabel detailsLabel = new JLabel(
				"Details (select a material to see the details)");
		
		add(detailsLabel);

	}

	public DetailsPanel(String productID, ManagerDAO dao, JPanel content) {

		this.ID = Integer.parseInt(productID);
		this.dao = dao;
		this.content = content;
		addDetails();

	}

	private void addDetails() {
		
		setLayout(new BorderLayout());
		// Label for the section
		JLabel detailsLabel = new JLabel(
				"Details of chosen material");
		detailsLabel.setHorizontalAlignment(JLabel.CENTER);
		add(detailsLabel, BorderLayout.NORTH);

		// Table columns definition
		String columnNames[] = { "Name", "Value" };

		// Defining table model
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {

			@Override
			// Enabling only specific cells for edit - Material Details
			public boolean isCellEditable(int row, int column) {
				
				if (column == 1 && row !=6){
					return true;
				} else {
					return false;
				}
			}
		};

		// Retrieving data for the list
		List<MaterialDetails> details = dao.getDetails();
		
		final MaterialDetails material = details.get((ID-1));
		// Adding rows to table model with values of given properties 
		Object[] name = { "Name", material.getName()};
		tableModel.addRow(name);
		Object[] description = { "Description", material.getDescription()};
		tableModel.addRow(description);
		Object[] notes = { "Notes", material.getNotes()};
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
		
		// Table action listener - "Save" and "Restore data" buttons toggle
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						
						JPanel buttons = new JPanel();
						JButton saveButton = new JButton("Save");
						
						// Action listener for "Save" button - data in the DAO list is being replaced with values from table
						saveButton.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
									
								dao.setMaterialDetails(
										material.getId()-1, 
										table.getModel().getValueAt(0, 1).toString(),
										table.getModel().getValueAt(1, 1).toString(),
										table.getModel().getValueAt(2, 1).toString(),
										table.getModel().getValueAt(3, 1).toString(),
										table.getModel().getValueAt(4, 1).toString(),
										table.getModel().getValueAt(5, 1).toString()
								);
								validate();
								repaint();
							}
						});
						
						buttons.add(saveButton);
						JButton restoreButton = new JButton("Restore data");
						
						// Action listener for "Restore data" button
						restoreButton.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								
								// Message dialog appears - user confirmation is required
								JLabel message = new JLabel("Do you want to restore the data?");	
								int reply = JOptionPane.showConfirmDialog(null, message, "Confirm data restore", JOptionPane.YES_NO_OPTION);
						        
								if (reply == JOptionPane.YES_OPTION) {
									try {
										// Data restore method called on DAO
										dao.restoreDetails();
									} catch (JsonParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (JsonMappingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									removeAll();
									add(new DetailsPanel());
									validate();
									repaint();
						        }
						        else {
						        	return;
						        }
			
							}
						});
						buttons.add(restoreButton);
						add(buttons, BorderLayout.SOUTH);
						validate();
					}
					

				});
		
		// Adding table to scrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);
	}

}
