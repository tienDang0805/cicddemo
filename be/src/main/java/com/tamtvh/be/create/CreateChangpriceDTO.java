package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateChangpriceDTO {
    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("NGAYTHAYDOI")
    private Date NGAYTHAYDOI;

    @JsonProperty("MANV")
    private String MANV;

    @JsonProperty("GIA")
    private Float GIA;
}
