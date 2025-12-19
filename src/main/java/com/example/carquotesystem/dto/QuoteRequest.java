package com.example.carquotesystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuoteRequest {

    private double gst;
    private double discount;
    private List<Long> serviceIds;

    // getters & setters
}

