/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author robert Wildman
 */
public class AdHocProject extends Project{
    
    private String projectEmployee;
    public AdHocProject()
    {
        super();
        
    }
    public AdHocProject(String projectId,String projectTitle, Calendar startDate,
                         String projectEmployee)
    {
        super(projectId,projectTitle,startDate);
        this.projectEmployee = projectEmployee;
        
    }

    public String getProjectEmployee() {
        return projectEmployee;
    }

    public void setProjectEmployee(String projectEmployee) {
        this.projectEmployee = projectEmployee;
    }

    @Override
    public String toString() {
        return "Project{" + "projectID=" + super.getProjectID() + ", projectTitle=" + getProjectTitle() + ", startDate=" + getFormattedStartDate() + ", startDate=" + projectEmployee +'}';
    }
    @Override
    public String toString(char delimiter) {
        final String QUOTE = "\"";
        final String EOLN = "\n";
        String output =  QUOTE + getProjectID() + QUOTE + delimiter +
                         QUOTE + getProjectTitle() + QUOTE + delimiter +
                         QUOTE + getFormattedStartDate() + QUOTE + delimiter +
                         QUOTE + "A"+ QUOTE + delimiter +
                         QUOTE + projectEmployee+ QUOTE + EOLN;
        return output;
    }
    
}
