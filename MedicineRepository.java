package com.sumati.nrsc.repository;

import com.sumati.nrsc.model.db.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByMedicineName(String medicineName);
}
