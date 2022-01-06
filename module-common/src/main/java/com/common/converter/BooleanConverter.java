package com.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute)
            return "Y";
        return "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData.equals("Y");
    }
}
