
package com.example.carquotesystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponseDTO {

    private Long quoteId;
    private String carModel;
    private double basePrice;
    private double discount;
    private double gstAmount;
    private double vasTotal;
    private double finalPrice;
    private List<ValueAddedServiceDTO> services;
}

