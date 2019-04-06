package com.sumati.nrsc.model.request;

import lombok.Data;

@Data
public class AddStoreRequest {

    String storeName;
    Point storeLocation;
}
