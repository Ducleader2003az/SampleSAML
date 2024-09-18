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
@Table(name = "company")
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cmp_cd", nullable = false, length = 50)
    private String cmpCode;

    @Column(name = "cmp_name", nullable = false, length = 100)
    private String cmpName;

    @Column(name = "cmp_description", nullable = true, length = 255)
    private String cmpDesc;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "office_id", referencedColumnName = "id")
    private Office office;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "floor_id", referencedColumnName = "id")
    private Floor floor;

}
