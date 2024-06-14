package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ChangePriceDTO {
    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("NGAYTHAYDOI")
    private Date NGAYTHAYDOI;

    @JsonProperty("MANV")
    private String MANV;

    @JsonProperty("GIA")
    private Float GIA;

    private WinelineDTO wineline;

    private StaffDTO staff;

}
