/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceapp;

import controllers.AttendanceController_Increment1;
import controllers.AttendanceController_Increment2;
import controllers.AttendanceController_Increment3;
import controllers.AttendanceController_Increment4;
import controllers.AttendanceController_Increment5;

/**
 *
 * @author mga
 */
public class AttendanceApp {

    public static void run() {        
       System.out.println("Attendance App\n" + "==============\n\n");
       // First Increment of the Controller
       // AttendanceController_Increment1 attendanceController = new AttendanceController_Increment1();
       // Second Increment of the Controller
       //  AttendanceController_Increment2 attendanceController = new AttendanceController_Increment2();
       // Third Increment of the Controller
       // AttendanceController_Increment3 attendanceController = new AttendanceController_Increment3();
       // Forth Increment of the Controller
       // AttendanceController_Increment4 attendanceController = new AttendanceController_Increment4();
       // Fifth Increment of the Controller
       AttendanceController_Increment5 attendanceController = new AttendanceController_Increment5();
        
        attendanceController.run();
        
        System.out.println("Thank you for using Attendance App. Goodbye.\n");        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AttendanceApp attendanceApp = new AttendanceApp();
        attendanceApp.run();
    }
    
}
