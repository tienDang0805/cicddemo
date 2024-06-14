package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Wineline;
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
public class Ct_phieudatDTO {
    @JsonProperty("IDCTPD")
    private Integer IDCTPD;

    @JsonProperty("MAPD")
    private String MAPD;

    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("SOLUONG")
    private Integer SOLUONG;

    @JsonProperty("GIA")
    private Float GIA;

    private PhieudatDTO phieudat;

    private WinelineDTO wineline;

    private List<Ct_phieutraDTO> ct_phieutras;

}
