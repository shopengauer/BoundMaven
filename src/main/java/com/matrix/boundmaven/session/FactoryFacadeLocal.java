/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.Factory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface FactoryFacadeLocal {

    void create(Factory factory);

    void edit(Factory factory);

    void remove(Factory factory);

    Factory find(Object id);

    List<Factory> findAll();

    List<Factory> findRange(int[] range);

    int count();
    
}
