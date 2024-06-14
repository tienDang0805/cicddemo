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
public class TrademarkDTO {
    @JsonProperty("MATH")
    private String MATH;

    @JsonProperty("TENTH")
    private String TENTH;

    private List<WinelineDTO> winelines;

}
