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
public class CreateCtPromoDTO {
    @JsonProperty("MAKM")
    private String MAKM;
    @JsonProperty("MADONG")
    private String MADONG;
    @JsonProperty("PHANTRAMGIAM")
    private Integer PHANTRAMGIAM;

}
