
package com.example.carquotesystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carquotesystem.dto.QuoteRequest;
import com.example.carquotesystem.model.Quote;
import com.example.carquotesystem.repository.CarRepository;
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
	public Quote generateQuote(
	        @PathVariable Long carId,
	        @RequestBody QuoteRequest request) {

	    return quoteService.generateQuote(carId, request);
	}

}
