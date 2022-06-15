package org.maxkizi.userservice.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.maxkizi.userservice.converter.ApplicationUserConverter;
import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.dto.ServerStatisticDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.maxkizi.userservice.repository.ApplicationUserRepository;
import org.maxkizi.userservice.service.StatisticService;
import org.maxkizi.userservice.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class StatisticServiceImpl extends AbstractBaseService<ApplicationUser, Long, ApplicationUserRepository> implements StatisticService {

    private final ApplicationUserRepository repository;
    private final ApplicationUserConverter converter;
    @Value("${adultAge}")
    private Integer adultAge;

    @Override
    public ServerStatisticDto getStatistic(UserStatus userStatus, Boolean isAdult) {
        List<ApplicationUserDto> usersInStatus = null;
        if (isAdult) {
            usersInStatus = repository.findAdultApplicationUsersByStatus(userStatus.name(), adultAge).stream()
                    .map(converter::toDto).collect(Collectors.toList());
        } else {
            usersInStatus = repository.findUnderAgeApplicationUsersByStatus(userStatus.name(), adultAge).stream()
                    .map(converter::toDto).collect(Collectors.toList());
        }
        return buildDto(usersInStatus);
    }

    @Override
    public ServerStatisticDto getStatistic(UserStatus userStatus) {
        List<ApplicationUserDto> usersInStatus = repository.findApplicationUsersByUserStatus(userStatus).stream()
                .map(converter::toDto).collect(Collectors.toList());
        return buildDto(usersInStatus);
    }

    private ServerStatisticDto buildDto(List<ApplicationUserDto> users) {
        return ServerStatisticDto.builder()
                .userCount(repository.count())
                .users(users)
                .averageAge(users.stream().mapToInt(ApplicationUserDto::getAge).average().orElse(Double.NaN))
                .build();
    }
}
