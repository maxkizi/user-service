package org.maxkizi.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationUserStatusDto {
    private Long id;
    private String previousStatus;
    private String newStatus;
}
