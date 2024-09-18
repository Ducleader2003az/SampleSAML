package com.kokobato.huynhduc.kokobatodemo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserDTO {
    @NotEmpty(message = "Username is required")
    @Length(min = 6, max = 12, message = "Username must be between 6 and 12 characters")
    private String username;

    @NotEmpty(message = "Password is required")
    @Length(min = 8, max = 14, message = "Username must be between 8 and 14 characters")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Phone is required")
    @Pattern(regexp = "^(\\+84|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$", message = "Invalid phone number format")
    private String phone;

    private int roleId;
}
