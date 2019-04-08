package controllers;

import helpers.InputHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author mga
 */
public class AttendanceController_Increment2 {
    private final Repository repository;
    
    /**
     *
     */
        
    public AttendanceController_Increment2() {
        InputHelper inputHelper = new InputHelper();
        char c = inputHelper.readCharacter("Load an already existing Swipe File (Y/N)?");
        if (c == 'Y' || c == 'y') {
            String fileName = inputHelper.readString("Enter filename");               
            this.repository = new Repository(fileName);
        }
        else {
            this.repository = new Repository();
        }
        
    }
   
    /**
     *
     */
    public void run() {
        boolean finished = false;
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesInReverseDateTimeOrder();
                    break;                    
                case 'D': 
                    listSwipesWhichMatchCardId();
                    break;
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");       
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }    
    
    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        InputHelper inputHelper = new InputHelper();
        char c = inputHelper.readCharacter("Add (S)wipe or Add (V)istor Swipe? Please Enter S or V", "S/V");
        if (c == 'S' || c == 's') {
            String newCardId =
                    inputHelper.readString("Enter Card Id");
            String newRoom =
                        inputHelper.readString("Enter Room");
            Swipe s = new Swipe(newCardId,newRoom);
            repository.add(s);
            System.out.println("New Swipe Added");
        }
        else if (c == 'V' || c == 'v'){
                String newCardId =
                        inputHelper.readString("Enter Card Id");
            String newRoom =
                        inputHelper.readString("Enter Room");
            String newVisitorName =
                        inputHelper.readString("Enter Visitor Name");
            String newVisitorCompany =
                        inputHelper.readString("Enter Visitor Company");
            VisitorSwipe s = new VisitorSwipe(newCardId,newRoom,newVisitorName,newVisitorCompany);
            repository.add(s);
            System.out.println("New Visitor Swipe Added");
        }
        
        System.out.format("\033[31m%s\033[0m%n", "=========");       
    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        //Will loop through all the Swipes in the repository and then display the toString methord of each
        repository.getItems().forEach((s) -> {
            System.out.println(s.toString());
        });
        System.out.format("\033[31m%s\033[0m%n", "======");          
    }    
      

    private void listSwipesInReverseDateTimeOrder() {        
        System.out.format("\033[31m%s\033[0m%n", "Date Time Order");
        System.out.format("\033[31m%s\033[0m%n", "==============="); 
    }    
    
    private void listSwipesWhichMatchCardId() {
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");   
    }
}
