package com.casestudy.controller;

import com.casestudy.dto.CarDto;
import com.casestudy.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {

	@Autowired
	CarService carService;

	@GetMapping("/cars")
	private List<CarDto> getAllCars() {
		List<CarDto> cars = carService.getAllCars();
		return cars;
	}

	@GetMapping("/car/{id}")
	private CarDto getCar(@PathVariable("id") Long id) {
		CarDto car = carService.getCar(id);
		System.out.println("Getting into the real carController");
		if (ObjectUtils.isEmpty(car)) {
			System.out.println("Car is empty");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "car.notpresent");
		}
		return car;
	}

	@DeleteMapping("/car/{id}")
	private void deleteCar(@PathVariable("id") Long id) {
		carService.deleteCar(id);
	}

	@PostMapping("/car")
	private CarDto saveCar(@Valid @RequestBody CarDto carDto) {
		System.out.println("Controoler : carDto :" + carDto.getModel().getManufacturer().getManufacturer_id());
		return carService.mergeCar(carDto);
	}

	@PutMapping("/car")
	private CarDto updateCar(@Valid @RequestBody CarDto carDto) {
		return carService.mergeCar(carDto);
	}
}
