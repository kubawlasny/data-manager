/**
 * App - starter class for the application
 *
 */

package com.wlasny.datamanager;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wlasny.datamanager.ui.AppFrame;
import com.wlasny.model.Company;
import com.wlasny.model.ManagerDAO;
import com.wlasny.model.Material;
import com.wlasny.model.MaterialDetails;

public class App 
{
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, MalformedURLException, IOException
    {
    	
    	/*TODO:
    	 * - DAO instance
    	 * - change context so that I can handle GUI elements here
    	 * - add elements from list to JTable
    	 * - repaint, moving between cards etc.
    	 * - save changed data in Material Details (getID, name=... etc.)
    	 * - restore data
    	 * - authorization screen
    	 * */
    	
    	//DAO initialization
    	
    	ManagerDAO dao = ManagerDAO.getInstance();
    	
    	List<Company> companies = dao.getCompanies();
    	
    	for(Company comp : companies) {
		System.out.println(comp.toString());
		}
    	
    	List<Material> materials = dao.getMaterials();
    	
    	for(Material mat : materials) {
		System.out.println(mat.toString());
		}
    	
    	List<MaterialDetails> details = dao.getDetails();
    	//System.out.println(details.get(0).toString());
    	//System.out.println(details.toString());
    	for(MaterialDetails md : details) {
    		System.out.println(md.toString());
    		}
    	
    	//Frame initialization
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppFrame();
            }
        });
    	
    	
    	
    	
    	//ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
    	
    	
    	
    	//Company company = new Company();
    	
    	//Company[] myObjects = restTemplate.readValue("http://193.142.112.220:8337/companyList", Company[].class);
    	
    	
    	
    	
    	
    	//JsonFactory
    	
    	//JFrame f = (JFrame) context.getBean("dmframe");
    	
    	//JFrame f = new DMFrame();
    	//System.out.println( "Hello World!" );
    }
}
