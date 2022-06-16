package org.maxkizi.userservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.maxkizi.userservice.annotation.ValidBirthDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationUserDto {
    private Long id;
    @NotEmpty(message = "Имя должно быть указано")
    private String username;
    @NotEmpty(message = "Дата рождения должна быть указана")
    @ValidBirthDate(message = "Некорректная дата рождения")
    private String dateOfBirth;
    @NotEmpty(message = "Email должен быть указан")
    @Email(message = "Некорректный email", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    private int age;
    private String userStatus;
}
