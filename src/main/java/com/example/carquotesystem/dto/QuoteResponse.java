
package com.example.carquotesystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuoteResponse {

    private Long quoteId;
    private String carModel;
    private double basePrice;
    private double discount;
    private double gstAmount;
    private double vasTotal;
    private double finalPrice;
    private List<ValueAddedServiceDTO> services;
}

