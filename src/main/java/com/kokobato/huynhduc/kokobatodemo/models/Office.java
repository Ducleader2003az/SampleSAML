package com.kokobato.huynhduc.kokobatodemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "office")
@Builder
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "office_cd", nullable = false, length = 50)
    private String officeCode;

    @Column(name = "office_name", nullable = false, length = 100)
    private String officeName;

    @Column(name = "office_desc", nullable = true, length = 255)
    private String officeDesc;

    @Column(name = "office_address", nullable = true, length = 200)
    private String officeAddress;

}
