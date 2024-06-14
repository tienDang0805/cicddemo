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
@Table(name="phieutra")
@ToString
@ApiModel(value = "All details about the pts.")
@Accessors(chain = true)
public class Phieutra {

    @Id
    @Column(name = "MAPT", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String MAPT;

    @NotBlank(message = "Date is required")
    @Column(name = "NGAYTRA", nullable = false)
    private Date NGAYTRA;

//    @NotBlank(message = "Bill id is required")
//    @Column(name = "MAHD", nullable = false)
//    private String MAHD;

//    @NotBlank(message = "Staff id is required")
//    @Column(name = "MANV", nullable = false)
//    private String MANV;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAHD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @OneToMany(mappedBy = "phieutra", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_phieutra> ct_phieutras;


}
