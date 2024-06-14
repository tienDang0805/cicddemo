package com.tamtvh.be.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="phieudat")
@ToString
@ApiModel(value = "All details about the pds.")
@Accessors(chain = true)
public class Phieudat {

    @Id
    @Column(name = "MAPD", nullable = false)
    private String MAPD;

    @Column(name = "NGAYDAT")
    private Date NGAYDAT;

    @Column(name = "HONN")
    private String HONN;

    @Column(name = "TENNN")
    private String TENNN;

    @Column(name = "DIACHINN")
    private String DIACHINN;

    @Column(name = "SDTNN")
    private String SDTNN;

    @Column(name = "GHICHU")
    private String GHICHU;

    @Column(name = "TRANGTHAI")
    private String TRANGTHAI;

//    @NotBlank(message = "Staff id is required")
//    @Column(name = "MANVD", nullable = false)
//    private String MANV;

    @Column(name = "MANVGH")
    private String MANVGH;

//    @NotBlank(message = "Customer id is required")
//    @Column(name = "MAKH", nullable = false)
//    private String MAKH;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAKH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANVD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @OneToMany(mappedBy = "phieudat", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_phieudat> ct_phieudats;

    @OneToOne(mappedBy = "phieudat", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Bill bill;
}
