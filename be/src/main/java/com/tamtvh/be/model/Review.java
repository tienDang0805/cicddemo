package com.tamtvh.be.model;

import com.tamtvh.be.model.key.ReviewId;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(ReviewId.class)
@Table(name="danhgia")
@ToString
@ApiModel(value = "All details about the reviews.")
@Accessors(chain = true)
public class Review {
    private static final long serialVersionUID = 1L;

//    @Id
//    @Column(name = "MAKH", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String MAKH;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAKH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

//    @NotBlank(message = "Wineline id is required")
//    @Column(name = "MADONG", nullable = false)
//    private String MADONG;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @Id
    @Column(name = "NGAYDANHGIA", nullable = false)
    private Date NGAYDANHGIA;

    @Column(name = "NOIDUNG")
    private String NOIDUNG;

    @Column(name = "RATING")
    private Integer RATING;



}
