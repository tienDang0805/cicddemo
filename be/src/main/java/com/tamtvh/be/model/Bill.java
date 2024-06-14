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
@Table(name="hoadon")
@ToString
@ApiModel(value = "All details about the bills.")
@Accessors(chain = true)
public class Bill {

    @Id
    @Column(name = "MAHD", nullable = false)
    private String MAHD;

    @Column(name = "NGAY")
    private Date NGAY;

    @Column(name = "THANHTIEN")
    private Float THANHTIEN;

    @Column(name = "MASOTHUE")
    private String MASOTHUE;

//    @NotBlank(message = "Staff id is required")
//    @Column(name = "MANV", nullable = false)
//    private String MANV;

//    @NotBlank(message = "Pd id is required")
//    @Column(name = "MAPD", nullable = false)
//    private String MAPD;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "MAPD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phieudat phieudat;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Phieutra> phieutras;


}
