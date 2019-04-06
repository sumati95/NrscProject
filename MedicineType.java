package com.sumati.nrsc.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "medicineType")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineType {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @Column(name = "medicinetype_name")
    private String medicineTypeName;
}