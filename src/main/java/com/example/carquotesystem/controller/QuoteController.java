
package com.example.carquotesystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carquotesystem.dto.QuoteRequestDTO;
import com.example.carquotesystem.dto.QuoteResponseDTO;
import com.example.carquotesystem.dto.ValueAddedServiceDTO;
import com.example.carquotesystem.model.Quote;
import com.example.carquotesystem.service.QuoteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quotes")
public class QuoteController {

	private final QuoteService quoteService;

	public QuoteController(QuoteService s) {
		this.quoteService = s;
	}

	@PostMapping("/{carId}")
	public ResponseEntity<QuoteResponseDTO> generateQuote(
	        @PathVariable Long carId,
	        @RequestBody QuoteRequestDTO request) {
		QuoteResponseDTO dto = new QuoteResponseDTO();
		Quote quote = null;
		try {
			quote = quoteService.generateQuote(carId, request.getGst(), request.getDiscount(), request.getServiceIds());
			dto.setQuoteId(quote.getId());
			dto.setCarModel(quote.getCar().getModel());
			dto.setBasePrice(quote.getCar().getBasePrice());
			dto.setDiscount(request.getDiscount());
			dto.setGstAmount(quote.getGst());
			dto.setVasTotal(quote.getVasTotal());
			dto.setFinalPrice(quote.getFinalPrice());
			dto.setServices(valueAddedDTOs(request.getServiceIds()));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}catch(Exception ex) {
			System.out.println("Exception occured while generating Quote for this car");
		}
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	private List<ValueAddedServiceDTO> valueAddedDTOs(List<Long> ids){
		return quoteService.getValueAddedServices(ids).stream()
										.map(v -> {
											ValueAddedServiceDTO dto = new ValueAddedServiceDTO();
											dto.setId(v.getId());
											dto.setName(v.getName());
											dto.setPrice(v.getPrice());
											return dto;
										}).toList();
	}

}
