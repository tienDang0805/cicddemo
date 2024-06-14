package com.tamtvh.be.model.key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Ct_phieutraId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String phieutra;

    private Integer ct_phieudat;

}
