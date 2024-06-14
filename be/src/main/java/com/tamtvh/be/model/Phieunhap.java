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
@Table(name="phieunhap")
@ToString
@ApiModel(value = "All details about the pns.")
@Accessors(chain = true)
public class Phieunhap {

    @Id
    @Column(name = "MAPN", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String MAPN;

    @NotBlank(message = "Date is required")
    @Column(name = "NGAYLAP", nullable = false)
    private Date NGAYLAP;

//    @NotBlank(message = "Staff id is required")
//    @Column(name = "MANV", nullable = false)
//    private String MANV;

//    @NotBlank(message = "Order id is required")
//    @Column(name = "MADDH", nullable = false)
//    private String MADDH;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @OneToMany(mappedBy = "phieunhap", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_phieunhap> ct_phieunhaps;

    @OneToOne
    @JoinColumn(name = "MADDH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;


}
