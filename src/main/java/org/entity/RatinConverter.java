package org.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatinConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        Rating[] values = Rating.values();
        for(Rating value: values) {
            if (s.equals(value)) {
                return value;
            }
        }
        return null;
    }
}
