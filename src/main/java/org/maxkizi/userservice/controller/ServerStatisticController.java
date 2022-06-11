package org.maxkizi.userservice.controller;

import org.maxkizi.userservice.dto.ServerStatisticDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.maxkizi.userservice.controller.Controllers.STATISTIC;

@RestController
public class ServerStatisticController {
    @GetMapping(STATISTIC)
    public ServerStatisticDto getServerStatistic(@RequestParam(name = "status") String status,
                                                 @RequestParam(name = "isAdult", required = false) Boolean isAdult) {
        return null;
    }
}
