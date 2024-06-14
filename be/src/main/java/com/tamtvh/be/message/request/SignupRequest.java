package com.tamtvh.be.message.request;

import com.tamtvh.be.email.ValidEmail;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SignupRequest {

    @NotBlank(message = "password is required")
    @ValidEmail
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "role is required")
    private String role;

    @NotBlank(message = "name is required")
    private String firstName;

    @NotBlank(message = "name is required")
    private String lastName;

    @NotBlank(message = "gender is required")
    private String gender;

//    @NotBlank(message = "birthday is required")
    @DateTimeFormat
    private Date birthday;

    @NotBlank(message = "Email is required")
    private String address;

    @NotBlank(message = "phone is required")
    private String phone;

}
