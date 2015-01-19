/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.entity.Department;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Vasiliy
 */
@Named(value = "depManager")
@ViewScoped
public class DepManager implements Serializable {

    /**
     * Creates a new instance of DepManager
     */
    @Inject
    private Conversation conversation; 
    
    private String figly;
    
    @PostConstruct ///1
    private void init(){
    // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dep", depForEdit); 
        
        FacesMessage facesMessage = new FacesMessage("PostConstruct", figly);
      FacesContext.getCurrentInstance().addMessage(null, facesMessage);  
          //getFigly("QQQQQQQQQQQ"); // 2
    
    } 
    
    @PreDestroy
    private void remove(){
      FacesMessage facesMessage = new FacesMessage("PreDestroy", figly);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);  
       //  setFigly("QQQQQQQQQQQ");
    
    } 
    
     public void startConversation(){
         conversation.begin();
          
         
        // figly = "Figly";
         FacesMessage facesMessage = new FacesMessage("Диалог начат", figly);//3
          FacesContext.getCurrentInstance().addMessage(null, facesMessage);
     
     }
    
    
    public DepManager() {
    }

    public String getFigly() {
      ArrayList parentDataList = (ArrayList) RequestContext.getCurrentInstance().getAttributes().get("1");
        String tr = (String)(parentDataList.get(0));
        return tr;
    }

    public void setFigly(String figly) {
        
        this.figly = figly;
        FacesMessage facesMessage = new FacesMessage("Set Fygly", this.figly);
         FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
    }
    
//   public void openCreateDepartmentDialog(Object o){
//     
//         Department depForEdit = (Department)o;
//        //  setFigly(depForEdit.getDepartmentName());
////         FacesMessage facesMessage = new FacesMessage("Диалог начат", null);
////          FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//         Map<String,List<String>> map = new HashMap<>();
//         List<String> list = new ArrayList();
//         list.add("Kuka");
//         map.put("1", list);
//         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dep", depForEdit); 
//         RequestContext.getCurrentInstance().openDialog("addDepartmentDialog");  
//      
//      } 
    public void closeDialog(){
        RequestContext.getCurrentInstance().closeDialog("close");
    }
    
    
}
