/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Vasiliy
 */
@Embeddable
public class Bounds implements Serializable{
   
   
    @Basic(fetch = FetchType.EAGER, optional = false)
    @NotNull
    @Min(Long.MIN_VALUE)
    @Max(Long.MAX_VALUE)
    private long lowBound;
    
    @Basic(fetch = FetchType.EAGER, optional = false)
    @NotNull
    @Min(Long.MIN_VALUE)
    @Max(Long.MAX_VALUE)
    private long highBound;
    
        
    public long getHighBound() {
        return highBound;
    }

    public void setHighBound(long highBound) {
        this.highBound = highBound;
    }

    public long getLowBound() {
        return lowBound;
    }

    public void setLowBound(long lowBound) {
        this.lowBound = lowBound;
    }
 
    
}
