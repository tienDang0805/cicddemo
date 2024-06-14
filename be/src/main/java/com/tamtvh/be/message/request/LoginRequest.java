package com.tamtvh.be.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.email.ValidEmail;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class LoginRequest {

    @NotBlank(message = "email is required")
    @JsonProperty("USERNAME")
    private String USERNAME;

    @NotBlank(message = "password is required")
    @JsonProperty("PASSWORD")
    private String PASSWORD;
}
