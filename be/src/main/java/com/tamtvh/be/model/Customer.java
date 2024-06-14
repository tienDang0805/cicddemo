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
@Table(name="khachhang")
@ToString
@ApiModel(value = "All details about the users.")
@Accessors(chain = true)
public class Customer {

    @Id
    @Column(name = "MAKH")
    private String MAKH;

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

//    @Column(name = "MANQ")
//    private String MANQ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANQ")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role Role1;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phieudat> phieudats;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Review> reviews;
}
