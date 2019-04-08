/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import repositories.Repository;

/**
 *
 * @author rober
 */
public class DAOTextImpl implements DAOInterface {
    
    static final char DELIMITER=',';   

    @Override
    public Repository load(String filename) {
       Repository repository = new Repository();
       try (BufferedReader br = new BufferedReader(new FileReader(filename))) { 
            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));        
                int customerId = Integer.parseInt(temp[0]);
                String customerName = stripQuotes(temp[1]);
                Customer customer = new Customer(customerId, customerName);
                int noProjects = Integer.parseInt(temp[2]);            
                for (int i=0; i<noProjects; i++) {
                    String project = stripQuotes(temp[i+3]);
                    customer.addProjectToCustomer(project);
                }
                repository.getItems().add(customer);            
                line = br.readLine();                
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repository;
        
    }
    
    public void store(String filename, Repository repository) {
        try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repository.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }    
    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }
}


