package org.maxkizi.userservice.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.maxkizi.userservice.converter.ApplicationUserConverter;
import org.maxkizi.userservice.dto.ApplicationUserStatusDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.exception.UserNotFoundException;
import org.maxkizi.userservice.model.ApplicationUser;
import org.maxkizi.userservice.repository.ApplicationUserRepository;
import org.maxkizi.userservice.service.ApplicationUserService;
import org.maxkizi.userservice.service.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl extends AbstractBaseService<ApplicationUser, Long, ApplicationUserRepository>
        implements ApplicationUserService {

    private final ApplicationUserRepository repository;
    private final ApplicationUserConverter userConverter;

    @Override
    @Transactional
    public ApplicationUser create(ApplicationUser user) {
        user.setAge(calculateAge(user.getDateOfBirth()));
        return save(user);
    }

    @Override
    public ApplicationUser finnById(Long id) {
        return get(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public ApplicationUserStatusDto updateStatus(Long id, UserStatus userStatus) {
        String previousStatus = get(id).orElseThrow(UserNotFoundException::new).getUserStatus().name();
        getRepository().updateUserStatus(userStatus.name(), id);
        return ApplicationUserStatusDto.builder()
                .newStatus(userStatus.name())
                .previousStatus(previousStatus)
                .id(id)
                .build();
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
