package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWinetypeDTO {
    @JsonProperty("MALOAI")
    String MALOAI;

    @JsonProperty("TENLOAI")
    String TENLOAI;

}
