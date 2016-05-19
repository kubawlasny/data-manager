package com.wlasny.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
	
	private String companyName;
	private int companyID;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	
	// Test method for printing company info in Console
	@Override
    public String toString() {
        return "Company: " + companyName + ", ID: " + companyID;
    }	
	
}
