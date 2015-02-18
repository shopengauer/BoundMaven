/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.contract.Factory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vasiliy
 */
@Stateless
public class FactoryFacade extends AbstractFacade<Factory> implements FactoryFacadeLocal {
    @PersistenceContext(unitName = "BoundMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactoryFacade() {
        super(Factory.class);
    }

    @Override
    public void create(Factory entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
