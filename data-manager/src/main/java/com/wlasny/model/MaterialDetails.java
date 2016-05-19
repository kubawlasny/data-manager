/*
 * MaterialDetails - model for instances of concrete material details 
 * retrieved from REST web service 
 * */

package com.wlasny.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialDetails {
	
	private String name;
	private String description;
	private String notes;
	private String supplier;
	private String price;
	private String currency;
	private int ID;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonProperty("ID")
	public int getId() {
		return ID;
	}


	public void setId(int id) {
		this.ID = id;
	}

	// Test method for printing material info in Console
	@Override
    public String toString() {
        return name+" "+
        		description+" "+
        		notes+" "+
        		supplier+" "+
        		price+" "+
        		currency+" "+
        		ID;
    }

}
