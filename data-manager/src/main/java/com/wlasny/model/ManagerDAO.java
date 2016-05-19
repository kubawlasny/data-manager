package com.wlasny.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ManagerDAO {
	
	//Lists of companies, materials and material details from REST service
	private List<Company> companies;
	private List<Material> materials;
	//private List<MaterialDetails> details;
	private MaterialDetails details;
	
	// DAO constructor - 
	public ManagerDAO() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		retrieveCompanies();
		retrieveMaterials();
		retrieveMaterialDetails();
	}
	
	private void retrieveCompanies(){
		
		RestTemplate restTemplate = new RestTemplate();
    	
		String json = "http://193.142.112.220:8337/companyList";
		
		ResponseEntity<List<Company>> companyResponse =
		        restTemplate.exchange(json,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {
		            });
		companies = companyResponse.getBody();
	}
	
	// Retrieve all materials and save in private member list
	private void retrieveMaterials(){
		
		RestTemplate restTemplate = new RestTemplate();
    	
		String json = "http://193.142.112.220:8337/materialList";
		
		ResponseEntity<List<Material>> response =
		        restTemplate.exchange(json,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Material>>() {
		            });
		materials = response.getBody();
	}
	
	// Companies list getter
	public List<Company> getCompanies() {
		return companies;
	}
	
	// Materials list getter
	public List<Material> getMaterials() {
		return materials;
	}

	// TODO: download details of a specific material
	private void retrieveMaterialDetails() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();

		//JSON from URL to Object
		MaterialDetails obj = mapper.readValue(new URL("http://193.142.112.220:8337/materialDetails?ID=1"), MaterialDetails.class);
		
		details = obj;
		
	}
	
	public MaterialDetails getDetails() {
		return details;
	}
	

	



}
