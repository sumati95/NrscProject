package com.sumati.nrsc.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "medicines")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "medicine_type")
    private String medicineType;
}
