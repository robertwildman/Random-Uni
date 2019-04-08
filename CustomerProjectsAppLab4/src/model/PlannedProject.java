/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author rober
 */
public class PlannedProject extends Project {
    
    private String[] set;
    private int noAllocatedDays;

    public String[] getSet() {
        return set;
    }

    public void setSet(String[] set) {
        this.set = set;
    }

    public int getNoAllocatedDays() {
        return noAllocatedDays;
    }

    public void setNoAllocatedDays(int noAllocatedDays) {
        this.noAllocatedDays = noAllocatedDays;
    }

    public PlannedProject() {
       
    }

    public PlannedProject(String[] set, int noAllocatedDays, String projectID, String projectTitle, Calendar startDate) {
        super(projectID, projectTitle, startDate);
        this.set = set;
        this.noAllocatedDays = noAllocatedDays;
    }
    
    @Override
    public String toString() {
        String user = "";
        for (String str : this.set)
        {
            user += str + " ";
        }
        return "Project{" + "projectID=" + super.getProjectID() + ", projectTitle=" + getProjectTitle() + ", startDate=" + getFormattedStartDate() + ", Users = " + user +", noAllocatedDays=" + noAllocatedDays +'}';
    }
    @Override
    public String toString(char delimiter) {
        final String QUOTE = "\"";
        final String EOLN = "\n";
        String users = "";
        for (String str : this.set)
            users += QUOTE + str + QUOTE + delimiter;
        String output =  QUOTE + getProjectID() + QUOTE + delimiter +
                         QUOTE + getProjectTitle() + QUOTE + delimiter +
                         QUOTE + getFormattedStartDate() + QUOTE + delimiter +
                         QUOTE + "P"+ QUOTE + delimiter + users +
                         noAllocatedDays + EOLN;
        return output;
    }
    
}
