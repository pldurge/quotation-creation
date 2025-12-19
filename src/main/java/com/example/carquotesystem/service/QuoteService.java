
package com.example.carquotesystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

	public Quote generateQuote(Long carId, double gst, double discount, List<Long> ids) throws ResourceNotFoundException {

		Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
		List<ValueAddedService> services =  getValueAddedServices(ids);
		double vasTotal = services.stream().mapToDouble(s -> s.getPrice()).sum();
		double taxableAmount = car.getBasePrice() + vasTotal - discount;
		double gstAmount = taxableAmount * gst / 100;
		double finalPrice = taxableAmount + gstAmount;

		Quote quote = new Quote();
		quote.setCar(car);
		quote.setBasePrice(car.getBasePrice());
		quote.setDiscount(discount);
		quote.setGst(gstAmount);
		quote.setVasTotal(vasTotal);
		quote.setFinalPrice(finalPrice);
		quote.setServices(services);

		return quoteRepository.save(quote);
	}
	
	public List<ValueAddedService> getValueAddedServices(List<Long> ids){
		return vasRepository.findAllById(ids);
	}
	

}
