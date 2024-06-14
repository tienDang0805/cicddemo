package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Customer;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class RoleDTO {
    @JsonProperty("MANQ")
    private String MANQ;

    @JsonProperty("TENNQ")
    private String TENNQ;

    private Collection<CustomerDTO> customers;

    private Collection<StaffDTO> staffs;
}
