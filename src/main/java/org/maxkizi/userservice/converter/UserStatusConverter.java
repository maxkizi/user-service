package org.maxkizi.userservice.converter;

import org.maxkizi.userservice.enumeration.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {
    @Override
    public String convertToDatabaseColumn(UserStatus userStatus) {
        return userStatus.getTextStatus();
    }

    @Override
    public UserStatus convertToEntityAttribute(String textStatus) {
        return UserStatus.findByTextValue(textStatus);
    }
}
