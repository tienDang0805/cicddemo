package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.dto.Ct_phieudatDTO;
import com.tamtvh.be.model.Ct_phieudat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePhieudatDTO {
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

}
