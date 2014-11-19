/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.session;

import com.matrix.boundmaven.entity.techobjects.DeviceVersion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vasiliy
 */
@Local
public interface DeviceVersionFacadeLocal {

    void create(DeviceVersion deviceVersion);

    void edit(DeviceVersion deviceVersion);

    void remove(DeviceVersion deviceVersion);

    DeviceVersion find(Object id);

    List<DeviceVersion> findAll();

    List<DeviceVersion> findRange(int[] range);

    int count();
    
}
