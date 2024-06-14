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
public class CreateBillDTO {
    @JsonProperty("MAHD")
    private String MAHD;
    @JsonProperty("NGAY")
    private Date NGAY;
    @JsonProperty("THANHTIEN")
    private Float THANHTIEN;
    @JsonProperty("MASOTHUE")
    private String MASOTHUE;
    @JsonProperty("MANV")
    private String MANV;
    @JsonProperty("MAPD")
    private String MAPD;

}
