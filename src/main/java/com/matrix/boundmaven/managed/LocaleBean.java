/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vasiliy
 */
@Named(value = "locale")
@SessionScoped
public class LocaleBean implements Serializable{

    /**
     * Creates a new instance of AppSet
     */
     
     private Locale locale;   
     
     
     public LocaleBean() {
       }
    
     
      @PostConstruct
      private void init(){
          locale = new Locale("ru");
       }         
     
     
    public String englishAction(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getViewRoot().setLocale(Locale.ENGLISH);
        locale = new Locale("en");
        return null;
    }
   
    
    public String russianAction(){
       FacesContext facesContext = FacesContext.getCurrentInstance();
       facesContext.getViewRoot().setLocale(new Locale("ru"));
       locale = new Locale("ru");
       return null;    
    }
     
    
    public Locale getLocale() {
        return locale;
    }
   
}
