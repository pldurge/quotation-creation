package com.example.carquotesystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteRequestDTO {
	
    private double gst;
    private double discount;
    private List<Long> serviceIds;
}
