/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.managed.deviceobject;

import com.matrix.boundmaven.session.DeviceTypeFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Vasiliy
 */
@Named(value = "deviceTypeBean")
@ConversationScoped
public class DeviceTypeBean implements Serializable {

    /**
     * Creates a new instance of DeviceTypeBean
     */
    
    @EJB
    private DeviceTypeFacadeLocal deviceTypeFacadeLocal;
    
    @Inject
    private Conversation conversation;
    
    @NotBlank(message = "{devTypeNotBlank.message}")
    @NotNull
    @Size(min = 2,max = 45,message = "{devTypeLength.message}")
    private String devTypeName;
    
   
    private String devTypeDesc;
    
    
    @PostConstruct
    private void init(){
     conversation.begin();
    }
    
    
    public DeviceTypeBean() {
    }

    public String getDevTypeName() {
        return devTypeName;
    }

    public void setDevTypeName(String devTypeName) {
        this.devTypeName = devTypeName;
    }

    public String getDevTypeDesc() {
        return devTypeDesc;
    }

    public void setDevTypeDesc(String devTypeDesc) {
        this.devTypeDesc = devTypeDesc;
    }
    
    
    
    
}
