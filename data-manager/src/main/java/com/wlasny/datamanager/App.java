package com.wlasny.datamanager;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;

import com.wlasny.model.Company;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String json = "http://193.142.112.220:8337/companyList";
		
    	ResponseEntity<List<Company>> companyResponse =
    	        restTemplate.exchange(json,
    	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Company>>() {
    	            });
    	List<Company> companies = companyResponse.getBody();
    	
    	for(Company comp : companies) {
    		System.out.println(comp.toString());
    	}
    	
    	
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
    	
    	
    	
    	//Company company = new Company();
    	
    	//Company[] myObjects = restTemplate.readValue("http://193.142.112.220:8337/companyList", Company[].class);
    	
    	
    	
    	
    	
    	//JsonFactory
    	
    	//JFrame f = (JFrame) context.getBean("dmframe");
    	
    	//JFrame f = new DMFrame();
    	//System.out.println( "Hello World!" );
    }
}
