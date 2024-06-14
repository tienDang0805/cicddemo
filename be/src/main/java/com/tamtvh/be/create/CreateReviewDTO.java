package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateReviewDTO {
    @JsonProperty("MAKH")
    String MAKH;

    @JsonProperty("MADONG")
    String MADONG;

    @JsonProperty("NGAYDANHGIA")
    Date NGAYDANHGIA;

    @JsonProperty("NOIDUNG")
    String NOIDUNG;

    @JsonProperty("RATING")
    Integer RATING;
}
