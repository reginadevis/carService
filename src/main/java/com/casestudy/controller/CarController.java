package com.casestudy.controller;

import com.casestudy.dto.CarDto;
import com.casestudy.service.CarService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import static java.time.temporal.ChronoUnit.SECONDS;

@RestController
public class CarController {

	@Autowired
	CarService carService;

	@GetMapping("/cars")
	private List<CarDto> getAllCars() {
		return carService.getAllCars();
	}


	@GetMapping("/car/{id}")
	private CarDto getCar(@PathVariable("id") Long id) {
		return carService.getCar(id);
	}

	@DeleteMapping("/car/{id}")
	private void deleteCar(@PathVariable("id") Long id) {
		carService.deleteCar(id);
	}

	@PostMapping("/car")
	private CarDto saveCar(@Valid @RequestBody CarDto carDto) {
		return carService.mergeCar(carDto);
	}

	@PutMapping("/car")
	private CarDto updateCar(@Valid @RequestBody CarDto carDto) {
		return carService.mergeCar(carDto);
	}

	public String getCarFail(@PathVariable("id") Long id){
		return "Car Service failed to return details for ID : "+id;
	}
}
