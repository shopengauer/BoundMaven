/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.boundmaven.converters;

import com.matrix.boundmaven.entity.partreference.RowObject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Vasiliy
 */
@Converter
public class TestConverter implements AttributeConverter<Byte[],RowObject>{

    @Override
    public RowObject convertToDatabaseColumn(Byte[] x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte[] convertToEntityAttribute(RowObject y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
