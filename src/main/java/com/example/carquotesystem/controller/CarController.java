package com.example.carquotesystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carquotesystem.dto.AddCarRequestDTO;
import com.example.carquotesystem.dto.AddCarResponseDTO;
import com.example.carquotesystem.dto.ResponseStatus;
import com.example.carquotesystem.model.Car;
import com.example.carquotesystem.service.CarService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cars")
public class CarController {
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/getallcars")
	public ResponseEntity<List<Car>> getAllCars(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.getAllCars());
	}
	
	@PostMapping("/addcar")
	public ResponseEntity<AddCarResponseDTO> addCar(@RequestBody AddCarRequestDTO requestDTO){
		AddCarResponseDTO dto = new AddCarResponseDTO();
		Car car = carService.addCar(requestDTO.getModel(), requestDTO.getBasePrice());
		dto.setId(car.getId());
		dto.setStatus(ResponseStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
}
