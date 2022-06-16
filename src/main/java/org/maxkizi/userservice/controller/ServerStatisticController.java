package org.maxkizi.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.userservice.dto.ServerStatisticDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.service.StatisticService;
import org.maxkizi.userservice.utils.UserStatusUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static org.maxkizi.userservice.controller.Controllers.STATISTIC;

@RestController
@RequiredArgsConstructor
public class ServerStatisticController {

    private final StatisticService statisticService;

    @GetMapping(STATISTIC)
    public ServerStatisticDto getServerStatistic(@RequestParam(name = "status") String status,
                                                 @RequestParam(name = "isAdult", required = false) Boolean isAdult) {

        UserStatus userStatus = UserStatusUtil.convertAndValidate(status);
        if (Objects.isNull(isAdult)) {
            return statisticService.getStatistic(userStatus);
        }
        return statisticService.getStatistic(userStatus, isAdult);
    }
}
