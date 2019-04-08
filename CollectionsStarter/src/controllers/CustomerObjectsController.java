/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import model.Customer;
import model.CustomerType;

/**
 *
 * @author mga
 */
public class CustomerObjectsController {
    Set<Customer> customers;
    //List<Customer> customers;    
    
    public CustomerObjectsController() {
        customers = new HashSet<>();
        //customers = new TreeSet<>(); 
        //customers = new LinkedList<>();         
    }
   
    public void run() {
        System.out.println("Add Elements");
        System.out.println("------------");
        addElements();
        displayElementsToString();
        displayElementsForEach();
        displayElementsIterator();  
        
        System.out.println("Remove Element");
        System.out.println("--------------");
        Customer c = new Customer("Puyol", "La Pobla de Segur", "charlie@barca.com", CustomerType.EXTERNAL_ACCOUNT);
        System.out.println("Element to be removed: " + c);
        c.setCustomerId(4);
        System.out.println("Element to be removed: " + c);        
        removeElement(c);
        displayElementsForEach();
        
//        System.out.println("Sort By Name");
//        System.out.println("------------");
//        Set<Customer> newSet = new TreeSet(Customer.CustomerNameComparator);
//        newSet.addAll(customers);        
//        System.out.println(newSet); 
    }

    private void addElements() {     
        customers.add(new Customer("Iniesta", "Fuentealbilla", "andy@barca.com", CustomerType.INTERNAL_ACCOUNT));
        customers.add(new Customer("Messi", "Rosario", "lionel@barca.com", CustomerType.INTERNAL_ACCOUNT));
        customers.add(new Customer("Busquets", "Sabadell", "sergi@barca.com", CustomerType.EXTERNAL_ACCOUNT));
        customers.add(new Customer("Puyol", "La Pobla de Segur", "charlie@barca.com", CustomerType.EXTERNAL_ACCOUNT));
        customers.add(new Customer("Pique", "Barcelona", "gerard@barca.com", CustomerType.EXTERNAL_NOACCOUNT));
        customers.add(new Customer("Puyol", "La Pobla de Segur", "charlie@barca.com", CustomerType.EXTERNAL_ACCOUNT));
    }
    
    private void removeElement(Customer toBeRemovedElement) {       
        if (customers.contains(toBeRemovedElement)) {
            boolean result = customers.remove(toBeRemovedElement);
            if (result)
                System.out.println("removed");
            else
                System.out.println("not present");
        }
        else
            System.out.println("not present");    
    }
    
    private  void displayElementsToString() {
        System.out.println("toString");
        System.out.println("========");
        System.out.println(customers);
    }
    
    private  void displayElementsForEach() {
        System.out.println("for each");
        System.out.println("========");
        for(Customer c : customers)
            System.out.println("customer:" + c);
    }
    
    private  void displayElementsIterator() {
        System.out.println("iterator");
        System.out.println("========");
        Iterator it = customers.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }
}
