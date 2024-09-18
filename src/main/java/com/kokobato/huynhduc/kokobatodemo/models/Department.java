package com.kokobato.huynhduc.kokobatodemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dpm_cd", nullable = false, length = 50)
    private String dpmCode;

    @Column(name = "dpm_name", nullable = false, length = 100)
    private String dpmName;

    @Column(name = "dpm_desc", nullable = true, length = 255)
    private String dpmDesc;

    @Column(name = "dpm_parent", nullable = true)
    private Integer dpmParent;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}
