package org.maxkizi.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.userservice.dto.ServerStatisticDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.maxkizi.userservice.controller.Controllers.STATISTIC;

@RestController
@RequiredArgsConstructor
public class ServerStatisticController {

    @GetMapping(STATISTIC)
    public ServerStatisticDto getServerStatistic(@RequestParam(name = "status") String status,
                                                 @RequestParam(name = "isAdult", required = false) Boolean isAdult) {
        return null;
    }
}
