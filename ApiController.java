package com.sumati.nrsc.controller;

import com.sumati.nrsc.model.db.*;
import com.sumati.nrsc.model.request.*;
import com.sumati.nrsc.model.response.SearchResponse;
import com.sumati.nrsc.repository.MedicineRepository;
import com.sumati.nrsc.repository.MedicineTypeRepository;
import com.sumati.nrsc.repository.StoreMedicineRepository;
import com.sumati.nrsc.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ApiController {
    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreMedicineRepository storeMedicineRepository;

    @Autowired
    MedicineTypeRepository medicineTypeRepository;

    @PostMapping("/searchForMedicines")
    public List<SearchResponse> searchStoresForMedicines(@RequestBody MedicineSearchRequest msr) {
        List<SearchResponse> results = new ArrayList<>();
        storeRepository.findAll().forEach(store -> {
            double distToStore = calculateDistanceInMeters(msr.getCurrentLocation().getLatitude(),
                    msr.getCurrentLocation().getLongitude(),
                    store.getLatitude(),
                    store.getLongitude());
            log.info("Distance to store {}, requested distance {}", distToStore, msr.getDistanceInMeters());
            if (distToStore < msr.getDistanceInMeters()) {
                results.add(SearchResponse.builder().storeName(store.getStoreName()).storeAddress(store.getStoreAddress()).build());
            }
        });
        return results;
    }

    @PostMapping("/addMedicineToStore")
    public StoreMedicine addMedicineToStore(@RequestBody AddMedicineToStoreRequest mar) {
        log.info("Adding medicine {} to store {}", mar.getMedicineName(), mar.getStoreName());
        Medicine medicine = medicineRepository.findByMedicineName(mar.getMedicineName());
        Store store = storeRepository.findByStoreName(mar.getStoreName());
        StoreMedicine storeMedicine = StoreMedicine.builder().medicine(medicine).store(store).build();
        storeMedicineRepository.save(storeMedicine);
        return storeMedicine;
    }

    @PostMapping("/addMedicine")
    public Medicine addMedicine(@RequestBody AddMedicineRequest mar) {
        log.info("Adding medicine {} of type {}", mar.getMedicineName(), mar.getMedicineType());
        Medicine medicine = Medicine.builder().medicineName(mar.getMedicineName()).medicineType(mar.getMedicineType()).build();
        medicineRepository.save(medicine);
        return medicine;
    }

    @PostMapping("/addMedicineType")
    public MedicineType addMedicineType(@RequestBody AddMedicineTypeRequest mtr) {
        log.info("Adding medicineType {} to medicine {}", mtr.getMedicineName());
        Medicine medicine = medicineRepository.findByMedicineName(mtr.getMedicineName());
        MedicineType medicineType = MedicineType.builder().build();
        medicineTypeRepository.save(medicineType);
        return medicineType;
    }

    @PostMapping("/addStores")
    public Store addStore(@RequestBody AddStoreRequest adr) {
        Store store = Store.builder().storeName(adr.getStoreName()).storeAddress(adr.getStoreLocation()).build();
        StoreRepository.save(store);
        return store;
    }

    static double calculateDistanceInMeters(double userLat, double userLng, double storeLat, double storeLng) {

        double dLat = Math.toRadians(storeLat - userLat);
        double dLon = Math.toRadians(storeLng - userLng);

        userLat = Math.toRadians(userLat);
        storeLat = Math.toRadians(storeLat);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(userLat) *
                        Math.cos(storeLat);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c * 1000;
    }
}


