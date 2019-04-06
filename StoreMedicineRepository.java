package com.sumati.nrsc.repository;

import com.sumati.nrsc.model.db.StoreMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMedicineRepository extends JpaRepository<StoreMedicine, Long> {
}
