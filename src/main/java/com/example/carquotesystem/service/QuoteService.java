
package com.example.carquotesystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.carquotesystem.dto.QuoteRequest;
import com.example.carquotesystem.dto.QuoteResponse;
import com.example.carquotesystem.dto.ValueAddedServiceDTO;
import com.example.carquotesystem.exception.ResourceNotFoundException;
import com.example.carquotesystem.model.Car;
import com.example.carquotesystem.model.Quote;
import com.example.carquotesystem.model.ValueAddedService;
import com.example.carquotesystem.repository.CarRepository;
import com.example.carquotesystem.repository.QuoteRepository;
import com.example.carquotesystem.repository.ValueAddedServiceRepository;

@Service
public class QuoteService {

	private final CarRepository carRepository;
	private final ValueAddedServiceRepository vasRepository;
	private final QuoteRepository quoteRepository;

	public QuoteService(CarRepository carRepository, ValueAddedServiceRepository vasRepository,
			QuoteRepository quoteRepository) {
		this.carRepository = carRepository;
		this.vasRepository = vasRepository;
		this.quoteRepository = quoteRepository;
	}

	public Quote generateQuote(Long carId, QuoteRequest request) {

		Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found"));

		List<ValueAddedService> services = vasRepository.findAllById(request.getServiceIds());

		double vasTotal = services.stream().mapToDouble(s -> s.getPrice()).sum();

		double taxableAmount = car.getBasePrice() + vasTotal - request.getDiscount();

		double gstAmount = taxableAmount * request.getGst() / 100;

		double finalPrice = taxableAmount + gstAmount;

		Quote quote = new Quote();
		quote.setBasePrice(car.getBasePrice());
		quote.setDiscount(request.getDiscount());
		quote.setGst(gstAmount);
		quote.setFinalPrice(finalPrice);
		quote.setServices(services);

		return quoteRepository.save(quote);
	}

	public QuoteResponse generateQuoteResponse(Long carId, QuoteRequest request) {

		Quote quote = generateQuote(carId, request);

		QuoteResponse response = new QuoteResponse();
		response.setQuoteId(quote.getId());
		response.setCarModel(quote.getCar().getModel());
		response.setBasePrice(quote.getBasePrice());
		response.setDiscount(quote.getDiscount());
		response.setGstAmount(quote.getGst());
		response.setFinalPrice(quote.getFinalPrice());

		List<ValueAddedServiceDTO> serviceDTOs = quote.getServices().stream().map(s -> {
			ValueAddedServiceDTO dto = new ValueAddedServiceDTO();
			dto.setId(s.getId());
			dto.setName(s.getName());
			dto.setPrice(s.getPrice());
			return dto;
		}).toList();

		response.setServices(serviceDTOs);

		double vasTotal = serviceDTOs.stream().mapToDouble(ValueAddedServiceDTO::getPrice).sum();

		response.setVasTotal(vasTotal);

		return response;
	}

}
