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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AdHocProject;
import model.Customer;
import model.PlannedProject;
import model.Project;
import repositories.Repository;

/**
 *
 * @author mga
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
                    line = br.readLine();
                    temp = line.split(Character.toString(DELIMITER));
                    String projectID = stripQuotes(temp[0]);
                    String projectTitle = stripQuotes(temp[1]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.parse(stripQuotes(temp[2]));
                    Calendar startDate = sdf.getCalendar();
                    String projectType = stripQuotes(temp[3]);
                    if(projectType.equalsIgnoreCase("P"))
                    {
                          int no = temp.length - 5;
                          String[] set = new String[no];
                          for(int p =0; p < no;p++)
                          {
                              set[p] = stripQuotes(temp[4 + p]);
                          }
                          int alloweddays = Integer.parseInt(temp[4 + no]);
                          PlannedProject p = new PlannedProject(set,alloweddays,projectID,projectTitle,startDate);
                          customer.addProjectToCustomer(p);
                          
                    }else if(projectType.equalsIgnoreCase("A"))
                    {
                        String projectEmployee  = stripQuotes(temp[4]);
                        AdHocProject p = new AdHocProject(projectID,projectTitle,startDate,projectEmployee);
                        customer.addProjectToCustomer(p);
                    }
                }
                repository.add(customer);                
                line = br.readLine();                
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repository;
    }
    
    @Override
    public void store(String filename, Repository repository) {
        try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repository.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }
}
