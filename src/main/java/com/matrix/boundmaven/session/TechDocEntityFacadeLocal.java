/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.techobjects.TechDocEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface TechDocEntityFacadeLocal {

    void create(TechDocEntity techDocEntity);

    void edit(TechDocEntity techDocEntity);

    void remove(TechDocEntity techDocEntity);

    TechDocEntity find(Object id);

    List<TechDocEntity> findAll();

    List<TechDocEntity> findRange(int[] range);

    int count();
    
}
