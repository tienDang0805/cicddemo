package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ReviewDTO {
    @JsonProperty("MAKH")
    private String MAKH;

    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("NGAYDANHGIA")
    private Date NGAYDANHGIA;

    @JsonProperty("NOIDUNG")
    private String NOIDUNG;

    @JsonProperty("RATING")
    private Integer RATING;

    private CustomerDTO customer;

    private WinelineDTO wineline;

}
