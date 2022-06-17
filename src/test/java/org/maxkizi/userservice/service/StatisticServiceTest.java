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
        Assertions.assertEquals(createdUsers.size(), statisticService.getStatistic(UserStatus.NONE).getTotalUserCountInService());
    }

    @AfterEach
    void deleteAll(){
        repository.deleteAll();
        repository.flush();
    }

    @Test
    void getStatisticByStatus() {
        ServerStatisticDto statistic = statisticService.getStatistic(UserStatus.NONE);
        Assertions.assertEquals(createdUsers.size(), statistic.getUsersInStatistic().size());

        ApplicationUser user0 = createdUsers.get(0);
        ApplicationUser user1 = createdUsers.get(1);

        //Обновляем статусы: 1 онлайн, 1 офлайн. Два останутся none
        userService.updateStatus(user0.getId(), UserStatus.OFFLINE);
        userService.updateStatus(user1.getId(), UserStatus.ONLINE);

        //Для каждого статуса проверяем колличество пользователей в статусе и средний возраст
        ServerStatisticDto onlineStatusStatistic = statisticService.getStatistic(UserStatus.OFFLINE);
        Assertions.assertEquals(1, onlineStatusStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(user0.getAge(), onlineStatusStatistic.getAverageAgeInStatistic());

        ServerStatisticDto offlineStatusStatistic = statisticService.getStatistic(UserStatus.ONLINE);
        Assertions.assertEquals(1, offlineStatusStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(user1.getAge(), offlineStatusStatistic.getAverageAgeInStatistic());

        ServerStatisticDto noneStatusStatistic = statisticService.getStatistic(UserStatus.NONE);
        Assertions.assertEquals(2, noneStatusStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(IntStream.of(
                createdUsers.get(2).getAge(), createdUsers.get(3).getAge()).average().orElse(Double.NaN),
                noneStatusStatistic.getAverageAgeInStatistic());
    }

    @Test
    void getStatisticByStatusAndAdultAge(){
        ServerStatisticDto adultStatistic = statisticService.getStatistic(UserStatus.NONE, true);
        Assertions.assertEquals(3, adultStatistic.getUsersInStatistic().size());

        ServerStatisticDto underAgeStatistic = statisticService.getStatistic(UserStatus.NONE, false);
        Assertions.assertEquals(1, underAgeStatistic.getUsersInStatistic().size());

        //У единственного несовершеннлетнего пользователя меняем статус на Онлайн, перебираем различные варианты получения статистики,
        //провепяем средний возраст
        ApplicationUser underageUser = createdUsers.stream().filter(user -> user.getAge() == 0).findFirst().get();
        userService.updateStatus(underageUser.getId(), UserStatus.ONLINE);

        ServerStatisticDto onlineUnderageStatistic = statisticService.getStatistic(UserStatus.ONLINE, false);
        Assertions.assertEquals(1, onlineUnderageStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(underageUser.getAge(), onlineUnderageStatistic.getAverageAgeInStatistic());

        ServerStatisticDto onlineAdultStatistic = statisticService.getStatistic(UserStatus.ONLINE, true);
        Assertions.assertEquals(0, onlineAdultStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(Double.NaN, onlineAdultStatistic.getAverageAgeInStatistic());

        ServerStatisticDto offlineUnderAgeStatistic = statisticService.getStatistic(UserStatus.OFFLINE, false);
        Assertions.assertEquals(0, offlineUnderAgeStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(Double.NaN, offlineUnderAgeStatistic.getAverageAgeInStatistic());

        ServerStatisticDto offlineAdultStatistic = statisticService.getStatistic(UserStatus.OFFLINE, true);
        Assertions.assertEquals(0, offlineAdultStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(Double.NaN, offlineAdultStatistic.getAverageAgeInStatistic());

        ServerStatisticDto noneOnderAgeStatistic = statisticService.getStatistic(UserStatus.NONE, false);
        Assertions.assertEquals(0, noneOnderAgeStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(Double.NaN, noneOnderAgeStatistic.getAverageAgeInStatistic());

        ServerStatisticDto noneAdultStatistic = statisticService.getStatistic(UserStatus.NONE, true);
        Assertions.assertEquals(3, noneAdultStatistic.getUsersInStatistic().size());
        Assertions.assertEquals(createdUsers.stream()
                .filter(u->u.getAge() != 0)
                .mapToInt(ApplicationUser::getAge)
                .average().orElse(Double.NaN), noneAdultStatistic.getAverageAgeInStatistic());
    }
}