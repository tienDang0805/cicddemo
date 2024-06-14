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
public class UpdateCustomerDTO {

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
    @JsonProperty("PASSWORD")
    private String PASSWORD;


}
