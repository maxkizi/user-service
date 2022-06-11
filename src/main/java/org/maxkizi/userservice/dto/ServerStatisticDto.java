package org.maxkizi.userservice.dto;

import java.util.ArrayList;
import java.util.List;

public class ServerStatisticDto {
    private Long userCount;
    private Float averageAge;
    private List<UserDto> users = new ArrayList<>();
}
