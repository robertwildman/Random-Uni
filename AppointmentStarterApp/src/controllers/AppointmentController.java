package controllers;

import model.Appointment;
import appointmentapp.InputHelper;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mga
 */
public class AppointmentController {
    
    private ArrayList<Appointment> appointments;
    
    public AppointmentController() {      
        appointments = new ArrayList<>();
        InputHelper inputHelper = new InputHelper();
        String appointmentDate = inputHelper.readString("Enter Appointment Date (YYYYMMDD");
        String fileName = "appointments" + appointmentDate;
        char c = inputHelper.readCharacter("Load an already existing Appointment File (Y/N)?");
        if (c == 'Y') {
            char c1 = inputHelper.readCharacter("Load from a Text File (T) or Object File (O)?");
            if (c1 == 'T') {
                fileName += ".txt";
                loadAppointmentsFromTextFile(fileName);
            }
            else if (c1== 'O') {
                fileName += ".dat";
                loadAppointmentsFromObjectFile(fileName);
            }
        }          
    }
    
    private void loadAppointmentsFromTextFile(String fileName) {
        char DELIMITER=',';
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { 
            String customerName, localTimeStr;
            LocalTime appointmentTime;
            int appointmentLength;

            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));        
                customerName = stripQuotes(temp[0]);
                localTimeStr = stripQuotes(temp[1]);
                appointmentTime = LocalTime.parse(localTimeStr);
                appointmentLength = Integer.parseInt(temp[2]);    
                Appointment appointment = new Appointment(customerName, appointmentTime, appointmentLength); 
                appointments.add(appointment);  
                line = br.readLine();                
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }    
    
    private void storeAppointmentsToTextFile(String fileName) {
        char DELIMITER=',';       
        try (PrintWriter output = new PrintWriter(fileName)) {
            for (Appointment appointment:appointments) {
                output.print(appointment.toString(DELIMITER));
            }
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public void loadAppointmentsFromObjectFile(String filename) { 
         try{
            FileInputStream fin = new FileInputStream(filename);
            try (ObjectInputStream ois = new ObjectInputStream(fin)) {
                appointments = (ArrayList<Appointment>) ois.readObject();
            }
        }catch(IOException | ClassNotFoundException ex){
            System.out.println(ex);
            System.exit(0);
        } 
    }
    
    public void storeAppointmentsToObjectFile(String filename) { 
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(filename));       
            output.writeObject(appointments);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(AppointmentController.class.getName())
                  .log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(AppointmentController.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public void run() {
        boolean finished = false;
        do {
            System.out.println(appointments);
            char choice = displayAppointmentsMenu();
            switch (choice) {
                case 'A': 
                    addAppointment();
                    break;
                case 'B': 
                    cancelAppointment();
                    break;                      
                case 'F':
                    InputHelper inputHelper = new InputHelper();
                    char c = inputHelper.readCharacter("Store to a Text File (T) or Object File (O)?");
                    String fileName = inputHelper.readString("Enter File name"); 
                    if (c == 'T') {
                        storeAppointmentsToTextFile(fileName);
                    }
                    else if (c == 'O') {
                        storeAppointmentsToObjectFile(fileName);
                    }                    
                    finished = true;
            }
        } while (!finished);
    }

    private char displayAppointmentsMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add a new appointment");
        System.out.print("\tB. Cancel an appointment");
        System.out.print("\tF. Finish\n");         
        return inputHelper.readCharacter("Enter choice", "ABF");
    }    
    
    private void addAppointment() {
        InputHelper inputHelper = new InputHelper();
        String customerName = inputHelper.readString("Enter Customer Name");
        LocalTime appointmentTime = LocalTime.parse(inputHelper.readString("Enter Appointment Time (HH:mm)"));
        int appointmentLength = inputHelper.readInt("Enter Appointment Length", 120, 15);   
        Appointment newAppointment = new Appointment(customerName, appointmentTime, appointmentLength);               
        appointments.add(newAppointment);
    }
    
    private void cancelAppointment() {
        for (int i=0; i<appointments.size(); i++)
            System.out.println(Integer.toString(i) + ": " + appointments.get(i));
        InputHelper inputHelper = new InputHelper();
        int appointmentNumber = inputHelper.readInt("Enter Appointment Number", appointments.size()-1, 0);   
        appointments.remove(appointmentNumber);
    }

}
