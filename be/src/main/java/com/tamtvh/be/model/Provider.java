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
@Table(name="nhacungcap")
@ToString
@ApiModel(value = "All details about the roles.")
@Accessors(chain = true)
public class Provider {

    @Id
    @Column(name = "MANCC", nullable = false)
    private String MANCC;

    @Column(name = "TENNCC")
    private String TENNCC;

    @Column(name = "DIACHI")
    private String DIACHI;

    @Column(name = "EMAIL")
    private String EMAIL;

    @Column(name = "SDT")
    private String SDT;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Cungcap> cungcaps;

}
