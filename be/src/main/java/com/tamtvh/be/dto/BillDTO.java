package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class BillDTO {
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

    private PhieudatDTO phieudat;

    private StaffDTO staff;

    private List<PhieutraDTO> phieutras;

}
