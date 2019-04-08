/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rober
 */
public enum EmployeeRoleType {
    PROJECT_LEADER("A",6),
    ANALYST("B",3),
    DEVELOPER("B",3),
    TESTER("C",1);
    
    private final String role;
    private final int salaryGrade;

    
    EmployeeRoleType(String role,int salaryGrade)
    {
        this.role = role;
        this.salaryGrade = salaryGrade;
    }

    public String getRole() {
        return role;
    }

    public int getSalaryGrade() {
        return salaryGrade;
    }
    
    
    
}
