/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;


import com.matrix.boundmaven.session.EmployeeFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
 
 
/**
 *
 * @author Vasiliy
 */
@Named(value = "login")
@ViewScoped 
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of UserBean
     */
    
   // private User user;
   
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    
    private boolean login;
    HttpSession session;
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
    
//    @EJB
 //   UserFacadeLocal userFacade;
    
    public LoginBean() {
    }
    //@Size(min = 2,max = 25,message = "{firstnameLength.message}")
    @NotNull 
    @NotBlank(message = "{notBlankFirstname.message}") 
    private String account;
    
    @NotNull
    @NotBlank(message = "{notBlankPassword.message}")
    private String password;
            

    @PostConstruct
    private void init(){
         //  User user = new User(null,"account","password","firstname","lastname","accessPolicy");
         //  userFacade.create(user);
      //  session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      //  session.setAttribute("login", false);     
      login = false;
      this.account ="";
      this.password ="";
    }
    
    public String validateUser(){
       
//        user = userFacade.selectUserByAccountAndPassword(account, password);
//        if(user != null){
//            //  session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().;
//            //  session.setAttribute("account", user);
//            //  session.setAttribute("login", true);
//              login = true;
//              return "menuLayoutClient"; 
//        } 
        login = true;      
        return "mainMenuTemplate?faces-redirect=true"; 
        //return null;
    }
    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = false;
    }
    
    public String logout(){
        this.login = false;
        account = "";
        password = "";
      //  user.setFirstname("");
      //  user.setLastname("");
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       
        
        
        try{
           System.out.println(session.getCreationTime());
            session.invalidate();
            
           return "loginLayout?faces-redirect=true";
        }catch(NullPointerException e){
            
            return null;
        }
        
        
    }
    
     
    
    
    
     public void openMyDialog(){
        
        Map<String,List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(param);
        map.put("param", list);
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("flash", "qwerty");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("data",list);
        RequestContext.getCurrentInstance().openDialog("dialog",null,map);
    
     
     
     }

    
}
