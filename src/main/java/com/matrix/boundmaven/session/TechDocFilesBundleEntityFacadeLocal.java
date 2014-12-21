/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.techobjects.techdocuments.TechDocFilesBundleEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface TechDocFilesBundleEntityFacadeLocal {

    void create(TechDocFilesBundleEntity techDocFilesBundleEntity);

    void edit(TechDocFilesBundleEntity techDocFilesBundleEntity);

    void remove(TechDocFilesBundleEntity techDocFilesBundleEntity);

    TechDocFilesBundleEntity find(Object id);

    List<TechDocFilesBundleEntity> findAll();

    List<TechDocFilesBundleEntity> findRange(int[] range);

    int count();
    
}
