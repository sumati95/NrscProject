package com.sumati.nrsc.model.request;

import lombok.Data;

@Data
public class AddMedicineToStoreRequest {
    String medicineName;

    String storeName;
}
