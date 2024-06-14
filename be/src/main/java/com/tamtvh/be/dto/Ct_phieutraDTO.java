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
public class Ct_phieutraDTO {
    @JsonProperty("MAPT")
    private String MAPT;

    @JsonProperty("IDCTPD")
    private Integer IDCTPD;

    @JsonProperty("SOLUONG")
    private Integer SOLUONG;

    private PhieutraDTO phieutra;

    private Ct_phieudatDTO ct_phieudat;

}
