package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PhieunhapDTO {
    @JsonProperty("MAPN")
    private String MAPN;

    @JsonProperty("NGAYLAP")
    private Date NGAYLAP;

    @JsonProperty("MANV")
    private String MANV;

    @JsonProperty("MADDH")
    private String MADDH;

    private List<Ct_phieunhapDTO> ct_phieunhaps;

    private StaffDTO staff;

    private OrderDTO order;
}
