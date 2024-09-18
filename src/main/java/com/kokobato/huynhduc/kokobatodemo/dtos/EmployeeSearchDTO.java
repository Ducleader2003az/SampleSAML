package com.kokobato.huynhduc.kokobatodemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
public class EmployeeSearchDTO {
    private int departmentId;
    private String locationStatus;
    private String name;
}
