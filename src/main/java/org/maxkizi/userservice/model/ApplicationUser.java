package org.maxkizi.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.maxkizi.userservice.converter.DateOfBirthConverter;
import org.maxkizi.userservice.converter.UserStatusConverter;
import org.maxkizi.userservice.enumeration.UserStatus;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "application_user")
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationUser extends BaseEntity {

    @Column(name = "username")
    private String username;
    @Column(name = "date_of_birth")
    @Convert(converter = DateOfBirthConverter.class)
    private LocalDate dateOfBirth;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private int age;
    @Column(name = "user_status")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus userStatus = UserStatus.NONE;
}
