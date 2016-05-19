/*
 * Material - model for materials instances retrieved from REST web service 
 * */

package com.wlasny.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Material {

	private String name;
	private int ID;
	private int companyID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	@JsonProperty("ID")
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	// Test method for printing material info in Console
	@Override
    public String toString() {
        return "Material: " + name + ", ID: " + ID + ", Company ID: " + companyID;
    }
}
