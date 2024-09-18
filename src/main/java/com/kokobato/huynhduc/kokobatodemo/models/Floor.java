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
@Table(name = "floor")
@Builder
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "floor_cd", nullable = false, length = 50)
    private String floorCode;

    @Column(name = "floor_name", nullable = false, length = 100)
    private String floorName;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "office_id", referencedColumnName = "id")
    private Office office;
}
