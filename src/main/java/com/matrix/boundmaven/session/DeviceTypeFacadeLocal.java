/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.DeviceType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface DeviceTypeFacadeLocal {

    void create(DeviceType deviceType);

    void edit(DeviceType deviceType);

    void remove(DeviceType deviceType);

    DeviceType find(Object id);

    List<DeviceType> findAll();

    List<DeviceType> findRange(int[] range);

    int count();
    
}
