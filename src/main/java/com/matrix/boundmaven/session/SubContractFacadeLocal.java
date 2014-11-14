/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.SubContract;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface SubContractFacadeLocal {

    void create(SubContract subContract);

    void edit(SubContract subContract);

    void remove(SubContract subContract);

    SubContract find(Object id);

    List<SubContract> findAll();

    List<SubContract> findRange(int[] range);

    int count();
    
}
