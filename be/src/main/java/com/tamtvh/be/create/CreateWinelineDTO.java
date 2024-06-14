package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWinelineDTO {
    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("TENDONG")
    private String TENDONG;

    @JsonProperty("TRANGTHAI")
    private String TRANGTHAI;

    @JsonProperty("HINHANH")
    private String HINHANH;

    @JsonProperty("MOTA")
    private String MOTA;

    @JsonProperty("CHITIET")
    private String CHITIET;

    @JsonProperty("SOLUONGTON")
    private Integer SOLUONGTON;

    @JsonProperty("MALOAI")
    private String MALOAI;

    @JsonProperty("MATH")
    private String MATH;

    @JsonProperty("GIA")
    private Float GIA;

    @JsonProperty("MANV")
    private String MANV;
}
