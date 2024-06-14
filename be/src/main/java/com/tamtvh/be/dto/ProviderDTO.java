package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ProviderDTO {
    @JsonProperty("MANCC")
    private String MANCC;

    @JsonProperty("TENNCC")
    private String TENNCC;

    @JsonProperty("DIACHI")
    private String DIACHI;

    @JsonProperty("EMAIL")
    private String EMAIL;

    @JsonProperty("SDT")
    private String SDT;

    private List<OrderDTO> orders;

    private List<CungcapDTO> cungcaps;
}
