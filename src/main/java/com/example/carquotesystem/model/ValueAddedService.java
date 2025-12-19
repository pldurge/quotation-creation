package com.example.carquotesystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "value_added_services")
@Getter
@Setter
public class ValueAddedService extends BaseModel{
    private String name;
    private double price;

}
