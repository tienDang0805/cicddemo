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
@Table(name="nhomquyen")
@ToString
@ApiModel(value = "All details about the roles.")
@Accessors(chain = true)
public class Role {

    @Id
    @Column(name = "MANQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String MANQ;

    @Enumerated(EnumType.STRING)
    @Column(name = "TENNQ", nullable = false)
    private ERole TENNQ;

    @OneToMany(mappedBy = "Role1", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Customer> customers;

    @OneToMany(mappedBy = "Role2", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Staff> staffs;


}
