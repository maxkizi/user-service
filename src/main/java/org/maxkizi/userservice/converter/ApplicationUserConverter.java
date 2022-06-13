package org.maxkizi.userservice.converter;

import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.model.ApplicationUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserConverter {
    private final ModelMapper modelMapper = new ModelMapper();

    public ApplicationUserDto toDto(ApplicationUser user) {
        return modelMapper.map(user, ApplicationUserDto.class);
    }

    public ApplicationUser toEntity(ApplicationUserDto dto){
        return modelMapper.map(dto, ApplicationUser.class);
    }
 }
