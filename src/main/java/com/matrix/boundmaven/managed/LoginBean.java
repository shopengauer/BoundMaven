/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
 
 
/**
 *
 * @author Vasiliy
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of UserBean
     */
    
   // private User user;
    private boolean login;
    HttpSession session;
    
    
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
    
    
    
}
