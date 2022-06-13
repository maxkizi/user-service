package org.maxkizi.userservice.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter
public class DateOfBirthConverter implements AttributeConverter<LocalDate, java.sql.Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date.toLocalDate();
    }
}
