package com.sumati.nrsc.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "stores")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Store {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_address")
    private String storeAddress;

    @Column(name = "store_latitude")
    private double latitude;

    @Column(name = "store_longitude")
    private double longitude;
}
