package com.sumati.nrsc.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "store_medicines")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StoreMedicine {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    Medicine medicine;
}
