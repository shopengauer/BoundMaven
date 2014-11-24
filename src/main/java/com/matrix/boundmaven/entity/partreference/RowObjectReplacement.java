/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.entity.partreference;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Vasiliy
 */
@Entity
public class RowObjectReplacement implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWOBJECT_REPLACEMENT")
    private Long id;

    @Basic(optional = false)
    @Column(name = "REPLACEMENT") 
    private String replacement;
    
    @Basic(optional = false)
    @Column(name = "MANUFACTURE") 
    private String manufacture;
    
    @Basic(optional = false)
    @Column(name = "PRODUCTION_TIME") 
    private String prodTime;
    
    @Basic(optional = false)
    @Column(name = "MOQ") 
    private String moq;
    
    @Basic(optional = false)
    @Column(name = "PRICE_UNNIT") 
    private String priceUnit;
    
    @Basic(optional = false)
    @Column(name = "YOUR_PRICE") 
    private String yourPrice;
    
    @Basic(optional = false)
    @Column(name = "YOUR_CONTRACTOR") 
    private String yourContractor;
     
    
    @ManyToOne(optional = false)
    private RowObject rowObject;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    
    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getProdTime() {
        return prodTime;
    }

    public void setProdTime(String prodTime) {
        this.prodTime = prodTime;
    }

    public String getMoq() {
        return moq;
    }

    public void setMoq(String moq) {
        this.moq = moq;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getYourPrice() {
        return yourPrice;
    }

    public void setYourPrice(String yourPrice) {
        this.yourPrice = yourPrice;
    }

    public String getYourContractor() {
        return yourContractor;
    }

    public void setYourContractor(String yourContractor) {
        this.yourContractor = yourContractor;
    }

    public RowObject getRowObject() {
        return rowObject;
    }

    public void setRowObject(RowObject rowObject) {
        this.rowObject = rowObject;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RowObjectReplacement)) {
            return false;
        }
        RowObjectReplacement other = (RowObjectReplacement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matrix.boundmaven.entity.partreference.RowObjectReplacement[ id=" + id + " ]";
    }
    
}
