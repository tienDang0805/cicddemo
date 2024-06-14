package com.tamtvh.be.message.response;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Data
public class JwtResponse {
    private String accessToken;
    private String role;

    public JwtResponse(String accessToken, String role) {
        this.accessToken = accessToken;
        this.role = role;
    }
}
