package org.maxkizi.userservice.exception;

public final class Exceptions {
    public static final String USER_NOT_FOUND_MESSAGE = "Пользователь не найден";
    public static final String ILLEGAL_USER_STATUS_MESSAGE = "Некорректный статус пользователя. Статус должен быть: ONLINE, OFFLINE или NONE (нечувствителен к регистру)";

    private Exceptions() {

    }
}
