package org.maxkizi.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServerStatisticDto {
    private Long totalUserCountInService;
    private Double averageAgeInStatistic;
    private List<ApplicationUserDto> usersInStatistic = new ArrayList<>();
}
