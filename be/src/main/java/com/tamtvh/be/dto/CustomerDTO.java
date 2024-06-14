package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Role;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class CustomerDTO {
    @JsonProperty("MAKH")
    private String MAKH;

    @JsonProperty("HO")
    private String HO;

    @JsonProperty("TEN")
    private String TEN;

    @JsonProperty("GIOITINH")
    private String GIOITINH;

    @JsonProperty("NGAYSINH")
    private Date NGAYSINH;

    @JsonProperty("DIACHI")
    private String DIACHI;

    @JsonProperty("SDT")
    private String SDT;

    @JsonProperty("EMAIL")
    private String EMAIL;

    @JsonProperty("USERNAME")
    private String USERNAME;

    @JsonProperty("PASSWORD")
    private String PASSWORD;

    @JsonProperty("MANQ")
    private String MANQ;

    private Role role;

    private List<PhieudatDTO> phieudats;

    //private List<ReviewDTO> reviews;
}
