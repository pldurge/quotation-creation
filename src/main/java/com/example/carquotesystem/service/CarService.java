package com.example.carquotesystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.carquotesystem.model.Car;
import com.example.carquotesystem.repository.CarRepository;

@Service
public class CarService {
	private final CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	public List<Car> getAllCars(){
		return carRepository.findAll();
	}
	
	public Car addCar(String model, double basePrice) {
		Car car = new Car();
		car.setModel(model);
		car.setBasePrice(basePrice);
		return carRepository.save(car);
	}
}
