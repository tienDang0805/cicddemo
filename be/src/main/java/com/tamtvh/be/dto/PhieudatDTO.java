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
public class PhieudatDTO {
    @JsonProperty("MAPD")
    private String MAPD;

    @JsonProperty("NGAYDAT")
    private Date NGAYDAT;

    @JsonProperty("HONN")
    private String HONN;

    @JsonProperty("TENNN")
    private String TENNN;

    @JsonProperty("DIACHINN")
    private String DIACHINN;

    @JsonProperty("SDTNN")
    private String SDTNN;

    @JsonProperty("GHICHU")
    private String GHICHU;

    @JsonProperty("TRANGTHAI")
    private String TRANGTHAI;

    @JsonProperty("MANVD")
    private String MANVD;

    @JsonProperty("MANVGH")
    private String MANVGH;

    @JsonProperty("MAKH")
    private String MAKH;

    private CustomerDTO customer;

    private StaffDTO staff;

    private List<Ct_phieudatDTO> ct_phieudats;

    private BillDTO bill;
}
