 package repositories;

//import java.util.function.Predicate;

import daos.DAOTextImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.function.Predicate;
import model.Swipe;

//import model.Swipe;


public class Repository implements RepositoryInterface {
    private ArrayList<Swipe> items;    
    
    public Repository() {
        this.items = new ArrayList<>();       
    }
    
    public Repository(ArrayList<Swipe> items) {        
        this.items = items;
    }
    
    public Repository(String filename) {
        this();
        DAOTextImpl dao = new DAOTextImpl();
        this.items = dao.load(filename).getItems();  
    }
    
    @Override
    public ArrayList<Swipe> getItems() {        
        return this.items;
    }

    
    @Override
    public void setItems(ArrayList<Swipe> items) {        
        this.items = items;
    }
    
    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }
       
    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = e->e.getId() == id;       
        this.items.removeIf(predicate);
    }
    
    @Override
    public Swipe getItem(int id) {
        for (Swipe item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "\nItems: " + this.items;
    }    
    
    public String toString(char delimiter) {
        String output = "";
        for(Swipe s: this.items){
            output += s.toString(delimiter);
        }
        System.out.println(output);
        System.out.println("END OF FILE");
        return output;
    }
    @Override
    public void store(String filename) {       
        // create dao and execute store    
        DAOTextImpl dao = new DAOTextImpl();
        dao.store(filename, this);
    }        
}
