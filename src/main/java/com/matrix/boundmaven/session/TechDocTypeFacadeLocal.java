/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.techobjects.techdocuments.types.TechDocType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface TechDocTypeFacadeLocal {

    void create(TechDocType techDocType);

    void edit(TechDocType techDocType);

    void remove(TechDocType techDocType);

    TechDocType find(Object id);

    List<TechDocType> findAll();

    List<TechDocType> findRange(int[] range);

    int count();
    
}
