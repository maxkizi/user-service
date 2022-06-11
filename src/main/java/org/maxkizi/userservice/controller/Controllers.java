package org.maxkizi.userservice.controller;

public final class Controllers {
    private Controllers() {
    }

    private static final String BASE = "/api/v1";
    private static final String BY_ID = "/{id}";

    public static final String USER = BASE + "/user";
    public static final String USER_BY_ID = USER + BY_ID;
    public static final String STATISTIC = BASE + "/statistic";

}
