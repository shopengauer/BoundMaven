/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import java.util.logging.Logger;

/**
 *
 * @author Vasiliy
 */
public enum PhoneType {
   WORK("Рабочий"),
   WORKMOBILE("Рабочий мобильный"),
   HOMEMOBILE("Домашний мобильный");
    
   private PhoneType(String phoneType){
       this.phoneType = phoneType;
   }
   
   private String phoneType;

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
   
   
   
   
}
