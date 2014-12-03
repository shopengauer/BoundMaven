/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vasiliy
 */
@Embeddable
@Access(AccessType.FIELD)
public class Time implements Serializable{
    
   @Basic(optional = false) 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "INSERT_TIME", nullable = false, updatable = false)
   private Date insertTime;
   
   @Basic(optional = true)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "UPDATE_TIME", nullable = true, updatable = true)
   private Date updateTime;
 
//   @PrePersist
//   private void setInsertTime(){
//       this.insertTime = this.updateTime = new Date();
//   }
//   
//   @PreUpdate
//   private void setUpdateTime(){
//       this.updateTime = new Date();
//   }
//   
   
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
  
   
    
}
