package org.maxkizi.userservice.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserStatus {
    ONLINE("online"),
    OFFLINE("offline"),
    NONE("none");
    private String textStatus;

    public static UserStatus findByTextValue(String status) {
        return Arrays.stream(values())
                .filter(s -> s.getTextStatus().equals(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Статус не найден"));
    }
}
