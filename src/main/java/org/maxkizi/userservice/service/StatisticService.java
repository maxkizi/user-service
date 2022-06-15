package org.maxkizi.userservice.service;

import org.maxkizi.userservice.dto.ServerStatisticDto;
import org.maxkizi.userservice.enumeration.UserStatus;

public interface StatisticService {
    ServerStatisticDto getStatistic(UserStatus userStatus, Boolean isAdult);
    ServerStatisticDto getStatistic(UserStatus userStatus);
}
