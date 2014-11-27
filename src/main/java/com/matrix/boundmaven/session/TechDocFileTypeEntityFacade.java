/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.techobjects.TechDocFileTypeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vasiliy
 */
@Stateless
public class TechDocFileTypeEntityFacade extends AbstractFacade<TechDocFileTypeEntity> implements TechDocFileTypeEntityFacadeLocal {
    @PersistenceContext(unitName = "BoundMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TechDocFileTypeEntityFacade() {
        super(TechDocFileTypeEntity.class);
    }
    
}
