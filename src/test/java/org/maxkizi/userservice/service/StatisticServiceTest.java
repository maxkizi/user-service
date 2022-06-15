package org.maxkizi.userservice.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maxkizi.userservice.BaseIntegrationTest;
import org.maxkizi.userservice.TestDataProvider;
import org.maxkizi.userservice.dto.ServerStatisticDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.maxkizi.userservice.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

class StatisticServiceTest extends BaseIntegrationTest {

    private final StatisticService statisticService;
    private final TestDataProvider dataProvider;
    private final ApplicationUserRepository repository;
    private final ApplicationUserService userService;
    private List<ApplicationUser> createdUsers;

    @Autowired
    public StatisticServiceTest(StatisticService statisticService, TestDataProvider dataProvider,
                                ApplicationUserRepository repository, ApplicationUserService userService) {
        this.statisticService = statisticService;
        this.dataProvider = dataProvider;
        this.repository = repository;
        this.userService = userService;
    }

    /**
     * Создаётся 3 совершеннолетиних пользователя и 1 несовершеннолетних у всех статус "none"
     */
    @BeforeEach
    void setUp() {
        this.createdUsers = repository.saveAll(dataProvider.buildThreeAdultsAndOneUnderageUsers());
        repository.flush();
        Assertions.assertEquals(createdUsers.size(), statisticService.getStatistic(UserStatus.NONE).getUserCount());
    }

    @AfterEach
    void deleteAll(){
        repository.deleteAll();
        repository.flush();
    }

    @Test
    void getStatisticByStatus() {
        ServerStatisticDto statistic = statisticService.getStatistic(UserStatus.NONE);
        Assertions.assertEquals(createdUsers.size(), statistic.getUsers().size());

        ApplicationUser user0 = createdUsers.get(0);
        ApplicationUser user1 = createdUsers.get(1);

        //Обновляем статусы: 1 онлайн, 1 офлайн. Два останутся none
        userService.updateStatus(user0.getId(), UserStatus.OFFLINE);
        userService.updateStatus(user1.getId(), UserStatus.ONLINE);

        //Для каждого статуса проверяем колличество пользователей в статусе и средний возраст
        ServerStatisticDto onlineStatusStatistic = statisticService.getStatistic(UserStatus.OFFLINE);
        Assertions.assertEquals(1, onlineStatusStatistic.getUsers().size());
        Assertions.assertEquals(user0.getAge(), onlineStatusStatistic.getAverageAge());

        ServerStatisticDto offlineStatusStatistic = statisticService.getStatistic(UserStatus.ONLINE);
        Assertions.assertEquals(1, offlineStatusStatistic.getUsers().size());
        Assertions.assertEquals(user1.getAge(), offlineStatusStatistic.getAverageAge());

        ServerStatisticDto noneStatusStatistic = statisticService.getStatistic(UserStatus.NONE);
        Assertions.assertEquals(2, noneStatusStatistic.getUsers().size());
        Assertions.assertEquals(IntStream.of(
                createdUsers.get(2).getAge(), createdUsers.get(3).getAge()).average().orElse(Double.NaN),
                noneStatusStatistic.getAverageAge());
    }

    @Test
    void getStatisticByStatusAndAdultAge(){
        ServerStatisticDto adultStatistic = statisticService.getStatistic(UserStatus.NONE, true);
        Assertions.assertEquals(3, adultStatistic.getUsers().size());

        ServerStatisticDto underAgeStatistic = statisticService.getStatistic(UserStatus.NONE, false);
        Assertions.assertEquals(1, underAgeStatistic.getUsers().size());

        //У единственного несовершеннлетнего пользователя меняем статус на Онлайн, перебираем различные варианты получения статистики,
        //провепяем средний возраст
        ApplicationUser underageUser = createdUsers.stream().filter(user -> user.getAge() == 0).findFirst().get();
        userService.updateStatus(underageUser.getId(), UserStatus.ONLINE);

        ServerStatisticDto onlineUnderageStatistic = statisticService.getStatistic(UserStatus.ONLINE, false);
        Assertions.assertEquals(1, onlineUnderageStatistic.getUsers().size());
        Assertions.assertEquals(underageUser.getAge(), onlineUnderageStatistic.getAverageAge());

        ServerStatisticDto onlineAdultStatistic = statisticService.getStatistic(UserStatus.ONLINE, true);
        Assertions.assertEquals(0, onlineAdultStatistic.getUsers().size());
        Assertions.assertEquals(Double.NaN, onlineAdultStatistic.getAverageAge());

        ServerStatisticDto offlineUnderAgeStatistic = statisticService.getStatistic(UserStatus.OFFLINE, false);
        Assertions.assertEquals(0, offlineUnderAgeStatistic.getUsers().size());
        Assertions.assertEquals(Double.NaN, offlineUnderAgeStatistic.getAverageAge());

        ServerStatisticDto offlineAdultStatistic = statisticService.getStatistic(UserStatus.OFFLINE, true);
        Assertions.assertEquals(0, offlineAdultStatistic.getUsers().size());
        Assertions.assertEquals(Double.NaN, offlineAdultStatistic.getAverageAge());

        ServerStatisticDto noneOnderAgeStatistic = statisticService.getStatistic(UserStatus.NONE, false);
        Assertions.assertEquals(0, noneOnderAgeStatistic.getUsers().size());
        Assertions.assertEquals(Double.NaN, noneOnderAgeStatistic.getAverageAge());

        ServerStatisticDto noneAdultStatistic = statisticService.getStatistic(UserStatus.NONE, true);
        Assertions.assertEquals(3, noneAdultStatistic.getUsers().size());
        Assertions.assertEquals(createdUsers.stream()
                .filter(u->u.getAge() != 0)
                .mapToInt(ApplicationUser::getAge)
                .average().orElse(Double.NaN), noneAdultStatistic.getAverageAge());
    }
}