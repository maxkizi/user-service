package org.maxkizi.userservice.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.model.ApplicationUser;

@Mapper
public interface ApplicationUserConverter {
    @Mappings({
            @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    })
    ApplicationUserDto toDto(ApplicationUser entity);

    @Mappings({
            @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    })
    ApplicationUser toEntity(ApplicationUserDto userDto);
}
