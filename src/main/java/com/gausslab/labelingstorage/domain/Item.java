package com.gausslab.labelingstorage.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assetNumber;
    private String startDate;
    private String checkDate;
    private String useDepartment;
    private String manager;
    private String image;

    public Item(String assetNumber, String startDate, String checkDate, String useDepartment, String manager, String image){
        this.assetNumber = assetNumber;
        this.startDate = startDate;
        this.checkDate =checkDate;
        this.useDepartment= useDepartment;
        this.manager = manager;
        this.image = image;
    }

}
