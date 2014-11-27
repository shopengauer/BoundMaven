/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Vasiliy
 */
@Named(value = "renderBean")
@RequestScoped
public class RenderBean {

    /**
     * Creates a new instance of RenderBean
     */
    public RenderBean() {
    }
   
    private boolean isDepWestMenu;
    private boolean isDevWestMenu;

    
    public boolean getIsDevWestMenu() {
        return isDevWestMenu;
    }

    public void setIsDevWestMenu(boolean isDevWestMenu) {
        this.isDevWestMenu = isDevWestMenu;
        this.isDepWestMenu = false;
    }
    
    public boolean isIsDepWestMenu() {
        return isDepWestMenu;
    }

    public void setIsDepWestMenu(boolean isDepWestMenu) {
        this.isDepWestMenu = isDepWestMenu;
         this.isDevWestMenu = false;
    }

   public void actionListener(ActionEvent event){
       event.getComponent().toString();
   } 
    
}
