package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class CungcapDTO {
    @JsonProperty("MANCC")
    private String MANCC;

    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("GIA")
    private Float GIA;

    private ProviderDTO provider;

    private WinelineDTO wineline;

}
