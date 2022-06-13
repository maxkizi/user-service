package org.maxkizi.userservice.service;

import org.maxkizi.userservice.dto.ApplicationUserStatusDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;

public interface ApplicationUserService {
    ApplicationUser create(ApplicationUser user);

    ApplicationUser finnById(Long id);

    ApplicationUserStatusDto updateStatus(Long id, UserStatus userStatus);
}
