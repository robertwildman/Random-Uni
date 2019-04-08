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
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author rober
 */
public class DAOTextImpl {
    
    static final char DELIMITER = ',';
    
    public Repository load(String filename) {
        
        Repository repository = new Repository();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { 
            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));        
                int id = Integer.parseInt(temp[0]);
                String cardId = stripQuotes(temp[1]);
                String room = stripQuotes(temp[2]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                sdf.parse(stripQuotes(temp[3]));
                Calendar swipeDateTime = sdf.getCalendar();
                
                if(temp.length > 4)
                {     
                    String visitorName = stripQuotes(temp[4]);
                    String visitorCompany = stripQuotes(temp[5]);
                    VisitorSwipe vs = new VisitorSwipe(id,cardId,room,swipeDateTime,visitorName,visitorCompany);
                    repository.add(vs);  
                }else
                {
                    Swipe s = new Swipe(id,cardId,room,swipeDateTime);
                    repository.add(s); 
                }
                              
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
