package org.maxkizi.userservice.utils;

import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.exception.IllegalUserStatusException;

public final class UserStatusUtil {
    private UserStatusUtil() {
    }

    public static UserStatus convertAndValidate(String status) {
        try {
            return UserStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalUserStatusException();
        }
    }
}
