/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.enterprize;

import com.matrix.boundmaven.managed.enterprize.qualifiers.Transmitter;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Vasiliy
 */
@Named(value = "trans")
@RequestScoped 
//@SessionScoped
public class TransmittingBean implements Serializable {

    /**
     * Creates a new instance of TransmittingBean
     */
    @Inject
    private Conversation conversation;
    
    private String param1 ;
    private String param2;
     
    public TransmittingBean() {
    
        
    
    }
    
     public void beginConversation()
   {
      if (conversation.isTransient())
      {
          conversation.begin();
          param1 = "Param 1";
      
      }
   }
 
   public void endConversation()
   {
      if (!conversation.isTransient())
      {
          conversation.end();
      }
   }
    

    public String getParam1() {
        
        return param1;
    }

    public void setParam1(String param1) {
         
        this.param1 = param1;
    }

    public String getParam2() {
         return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
  
    
}
