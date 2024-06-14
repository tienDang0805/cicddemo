package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Staff;
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
public class PhieutraDTO {
    @JsonProperty("MAPT")
    private String MAPT;

    @JsonProperty("NGAYTRA")
    private Date NGAYTRA;

    @JsonProperty("MAHD")
    private String MAHD;

    @JsonProperty("MANV")
    private String MANV;

    private BillDTO bill;

    private StaffDTO staff;

    private List<Ct_phieutraDTO> ct_phieutras;
}
