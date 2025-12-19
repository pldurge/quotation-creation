
package com.example.carquotesystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quotes")
@Getter
@Setter

public class Quote extends BaseModel{
    @ManyToOne  
    @JoinColumn(name = "car_id")
    private Car car;
    private double basePrice;
    private double discount;
    private double gst;
    private double finalPrice;

    @ManyToMany
    @JoinTable(
        name = "quote_vas",
        joinColumns = @JoinColumn(name = "quote_id"),
        inverseJoinColumns = @JoinColumn(name = "vas_id")
    )
    private List<ValueAddedService> services = new ArrayList<>();
}

