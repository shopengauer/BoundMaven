/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.partreference.RowObject;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface RowObjectFacadeLocal {

    void create(RowObject rowObject);

    void edit(RowObject rowObject);

    void remove(RowObject rowObject);

    RowObject find(Object id);

    List<RowObject> findAll();

    List<RowObject> findRange(int[] range);

    int count();
    
}
