package com.hexagonal.hexagonal.infrastructure.utils;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender != null ? gender.getDisplayValue() : null;
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return dbData != null ? Gender.fromString(dbData) : null;
    }
}