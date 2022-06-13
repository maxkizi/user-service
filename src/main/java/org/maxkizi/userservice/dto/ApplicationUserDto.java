package org.maxkizi.userservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationUserDto {
    private Long id;
    private String username;
    private String dateOfBirth;
    private String email;
    private int age;
    private String userStatus;
}
