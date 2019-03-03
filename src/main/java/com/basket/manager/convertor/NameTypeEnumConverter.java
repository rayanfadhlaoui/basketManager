package com.basket.manager.convertor;

import com.basket.manager.entities.players.NameTypeEnum;

import javax.persistence.AttributeConverter;

public class NameTypeEnumConverter implements AttributeConverter<NameTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(NameTypeEnum attribute) {
        return attribute.getValue();
    }

    @Override
    public NameTypeEnum convertToEntityAttribute(String dbData) {
        return NameTypeEnum.getEnumFrom(dbData);
    }
}