package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProviderDTO {
    @JsonProperty("MANCC")
    String MANCC;

    @JsonProperty("TENNCC")
    String TENNCC;

    @JsonProperty("DIACHI")
    String DIACHI;

    @JsonProperty("EMAIL")
    String EMAIL;

    @JsonProperty("SDT")
    String SDT;
}
