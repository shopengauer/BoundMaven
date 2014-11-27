/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.partreference.RowObjectPartRef;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface RowObjectPartRefFacadeLocal {

    void create(RowObjectPartRef rowObjectPartRef);

    void edit(RowObjectPartRef rowObjectPartRef);

    void remove(RowObjectPartRef rowObjectPartRef);

    RowObjectPartRef find(Object id);

    List<RowObjectPartRef> findAll();

    List<RowObjectPartRef> findRange(int[] range);

    int count();
    
}
