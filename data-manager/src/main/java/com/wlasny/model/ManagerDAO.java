/**
 * ManagerDAO.java - data access object responsible for retrieving data from REST web service
 */
package com.wlasny.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ManagerDAO {
	
	private static ManagerDAO instance;
	
	//Lists of companies, materials and material details from REST service
	private List<Company> companies;
	private List<Material> materials;
	private List<MaterialDetails> details = new ArrayList<MaterialDetails>();
	
	// DAO constructor - 
	private ManagerDAO() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		retrieveCompanies();
		retrieveMaterials();
		retrieveMaterialDetails();
	}
	
	// Singleton pattern - one and only instance of DAO can be retrieved with following method
	public static ManagerDAO getInstance() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		if (instance == null) {
		instance = new ManagerDAO();
		}
		return instance;
		}
	
	// Retrieve all companies and save as objects
	private void retrieveCompanies(){
		
		RestTemplate restTemplate = new RestTemplate();
    	
		String json = "http://193.142.112.220:8337/companyList";
		
		ResponseEntity<List<Company>> companyResponse =
		        restTemplate.exchange(json,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {
		            });
		companies = companyResponse.getBody();
	}
	
	// Retrieve all materials and save as objects
	private void retrieveMaterials(){
		
		RestTemplate restTemplate = new RestTemplate();
    	
		String json = "http://193.142.112.220:8337/materialList";
		
		ResponseEntity<List<Material>> response =
		        restTemplate.exchange(json,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Material>>() {
		            });
		materials = response.getBody();
	}
	
	// Retrieve details of all materials and save as objects
	private void retrieveMaterialDetails() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{

		//JSON from URL to Object
		for (int i = 1; i <= materials.size(); i++) {
			ObjectMapper mapper = new ObjectMapper();
			MaterialDetails obj = mapper.readValue(new URL("http://193.142.112.220:8337/materialDetails?ID="+i), MaterialDetails.class);
			details.add(obj);
		}
	}
	
	// Method used for persistence of material details while the application is running
	public void setMaterialDetails(int id, String name, String description, String notes, String supplier, String price, String currency) {
		this.details.get(id).setName(name);
		this.details.get(id).setDescription(description);
		this.details.get(id).setNotes(notes);
		this.details.get(id).setSupplier(supplier);
		this.details.get(id).setPrice(price);
		this.details.get(id).setCurrency(currency);
	}
	
	// Restoring material details at user's request
	public void restoreDetails() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		details.clear();
		retrieveMaterialDetails();
	}
	
	// Companies list getter
	public List<Company> getCompanies() {
		return companies;
	}
	
	// Materials list getter
	public List<Material> getMaterials() {
		return materials;
	}
	// Material details list getter
	public List<MaterialDetails> getDetails() {
		return details;}
}