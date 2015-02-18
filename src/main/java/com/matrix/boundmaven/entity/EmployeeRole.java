/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

/**
 *
 * @author Василий
 */
public enum EmployeeRole {
  
    VIEWER("Просмотр"),
    EDITOR("Редактирование"),
    ADMIN("Администрирование");  

    private EmployeeRole(String label) {
        this.label = label;
    }
 
     
     private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
  
   public static EmployeeRole fromString(String label) {
    if (label != null) {
      for (EmployeeRole b : EmployeeRole.values()) {
        if (label.equalsIgnoreCase(b.label)) {
          return b;
        }
      }
    }
    return null;
  }
  
}
