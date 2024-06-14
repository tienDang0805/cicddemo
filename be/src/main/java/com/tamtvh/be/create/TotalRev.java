package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TotalRev {
    @JsonProperty("tong")
    Float tong;

    @JsonProperty("thang")
    Integer thang;

    @JsonProperty("nam")
    Integer nam;


    public TotalRev(Object[] object) {
        this.tong = Float.parseFloat(object[0].toString());
        this.thang = Integer.parseInt(object[1].toString());
        this.nam = Integer.parseInt(object[2].toString());
    }
}
