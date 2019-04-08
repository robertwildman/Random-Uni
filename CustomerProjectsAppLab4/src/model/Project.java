/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Robert Wildman
 */
public class Project {
   private String projectID;
   private String projectTitle;
   private Calendar startDate;
    public Project()
    {
        
    } 
    public Project(String projectID, String projectTitle,Calendar startDate)
    {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.startDate = startDate;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Project{" + "projectID=" + projectID + ", projectTitle=" + projectTitle + ", startDate=" + getFormattedStartDate() + '}';
    }
    public String toString(char delimiter) {
        final String QUOTE = "\"";
        final String EOLN = "\n";
        String output =  QUOTE + this.projectID + QUOTE + delimiter +
                         QUOTE + this.projectTitle + QUOTE + delimiter +
                         QUOTE + getFormattedStartDate() + QUOTE + EOLN;
        return output;
    }
    public String getFormattedStartDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = formatter.format(this.startDate.getTime());
        return dateString;
    }
}
