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
@Table(name="nhanvien")
@ToString
@ApiModel(value = "All details about the staffs.")
@Accessors(chain = true)
public class Staff {

    @Id
    @Column(name = "MANV", nullable = false)
    private String  MANV;

    @Column(name = "HO")
    private String HO;

    @Column(name = "TEN")
    private String TEN;

    @Column(name = "GIOITINH")
    private String GIOITINH;

    @Column(name = "NGAYSINH")
    private Date NGAYSINH;

    @Column(name = "DIACHI")
    private String DIACHI;

    @Column(name = "SDT")
    private String SDT;

    @Column(name = "EMAIL")
    private String EMAIL;

    @Column(name = "USERNAME")
    private String USERNAME;

    @Column(name = "PASSWORD")
    private String PASSWORD;

//    @Column(name = "MANQ", nullable = false)
//    private String MANQ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANQ")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role Role2;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phieudat> phieudats;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ChangePrice> changeprices;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Promotion> promotions;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Bill> bills;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phieutra> phieutras;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phieunhap> phieunhaps;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;
}
