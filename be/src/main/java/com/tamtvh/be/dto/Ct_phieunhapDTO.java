package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Phieunhap;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Ct_phieunhapDTO {
    @JsonProperty("MAPN")
    private String MAPN;

    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("SOLUONG")
    private Integer SOLUONG;

    @JsonProperty("GIA")
    private Float GIA;

    private PhieunhapDTO phieunhap;

    private WinelineDTO wineline;

}
