package com.tamtvh.be.model.key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Ct_orderId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String wineline;

    private String order;

}
