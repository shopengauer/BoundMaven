/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 *
 * @author Vasiliy
 */
@Embeddable
public class Scheme {
    
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Size()
    private String schemeName;
    
    private String schemeCod;
    
    private String schemeFileName;    
    
    @Lob
    @Column(length = 20947787)
    private Byte[] schemeFile; 
      
}
