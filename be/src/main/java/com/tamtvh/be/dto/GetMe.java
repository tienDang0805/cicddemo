package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMe {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("role")
    private String role;
    @JsonProperty("username")
    private String username;
}
