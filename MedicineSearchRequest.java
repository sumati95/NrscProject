package com.sumati.nrsc.model.request;

import lombok.Data;

@Data
public class MedicineSearchRequest {
    String medicineName;

    int distanceInMeters;

    Point currentLocation;
}