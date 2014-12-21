/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.techobjects.techdocuments.types.TechDocFileTypeEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface TechDocFileTypeEntityFacadeLocal {

    void create(TechDocFileTypeEntity techDocFileTypeEntity);

    void edit(TechDocFileTypeEntity techDocFileTypeEntity);

    void remove(TechDocFileTypeEntity techDocFileTypeEntity);

    TechDocFileTypeEntity find(Object id);

    List<TechDocFileTypeEntity> findAll();

    List<TechDocFileTypeEntity> findRange(int[] range);

    int count();
    
}
