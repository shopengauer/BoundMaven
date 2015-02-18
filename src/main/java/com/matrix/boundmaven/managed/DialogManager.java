/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Василий
 */
@Named(value = "dialogManager")
@ViewScoped 
public class DialogManager implements Serializable{

    /**
     * Creates a new instance of DialogManager
     */
    
    
    @Inject
    LoginBean login;
    
    private String param;
    
    
    
    @PostConstruct
    private void init(){
       // param = login.getParam();
      // ArrayList parentDataList = (ArrayList) RequestContext.getCurrentInstance().getAttributes().get("param");
       //Boolean b = parentDataList.isEmpty();
      List<String> list=(List)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("data");
      param = list.get(0);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("data");
    // param =  (String)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flash");
    
    }  
    
    
    public DialogManager() {
    }
     
     public void closeMyDialog(){
        
        
        RequestContext.getCurrentInstance().closeDialog(Boolean.TRUE);
    }

    
    
    
    
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
    
    
    
}
