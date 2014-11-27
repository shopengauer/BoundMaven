/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.partreference.RowObjectReplacement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface RowObjectReplacementFacadeLocal {

    void create(RowObjectReplacement rowObjectReplacement);

    void edit(RowObjectReplacement rowObjectReplacement);

    void remove(RowObjectReplacement rowObjectReplacement);

    RowObjectReplacement find(Object id);

    List<RowObjectReplacement> findAll();

    List<RowObjectReplacement> findRange(int[] range);

    int count();
    
}
