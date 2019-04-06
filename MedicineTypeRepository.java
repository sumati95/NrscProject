package com.sumati.nrsc.repository;

import com.sumati.nrsc.model.db.Medicine;
import com.sumati.nrsc.model.db.MedicineType;
import com.sumati.nrsc.model.db.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineTypeRepository extends JpaRepository<MedicineType, Long> {
}
