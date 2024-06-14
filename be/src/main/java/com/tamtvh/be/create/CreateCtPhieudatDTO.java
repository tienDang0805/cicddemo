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
public class CreateCtPhieudatDTO {
    @JsonProperty("MAPD")
    private String MAPD;

    @JsonProperty("MADONG")
    private String MADONG;
    @JsonProperty("SOLUONG")
    private Integer SOLUONG;
    @JsonProperty("GIA")
    private Float GIA;
}
