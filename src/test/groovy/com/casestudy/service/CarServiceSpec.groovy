package com.casestudy.service

import com.casestudy.dto.CarDto
import com.casestudy.dto.ManufacturerDto
import com.casestudy.dto.ModelDto
import com.casestudy.entity.Car
import com.casestudy.entity.Manufacturer
import com.casestudy.entity.Model
import com.casestudy.repository.CarRepository
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class CarServiceSpec extends Specification{

    CarRepository carRepository = Mock(CarRepository)
    def carService = new CarService(carRepository)

    def testgetCar(){
        given:
            def expectedCar = getCarOuptut()
            def mockCar = getMockCar()
            carRepository.findById(4) >> Optional.of(mockCar)

        when:
            def actualCar = carService.getCar(4)

        then:
            actualCar == expectedCar
    }

    def testgetCarNegative(){
        given:

        when:
        def actualCar = carService.getCar(4)

        then:
        thrown(ResponseStatusException)
    }

    def testgetAllCars(){
        given:
        def expectedCars = getCarOuptuts()
        def mockCars = getMockCars()
        carRepository.findAll() >> mockCars

        when:
        def actualCars = carService.getAllCars()

        then:
        actualCars == expectedCars
    }

    def testgetAllCarsNegative(){
        given:

        when:
        def actualCars = carService.getAllCars()

        then:
        thrown(ResponseStatusException)
    }

    def testDeleteCar(){
        given:
        carRepository.deleteById(4) >> {}

        when:
        carService.deleteCar(4)

        then:
        noExceptionThrown()
    }

    def testDeleteCarNegative(){
        given:
        carRepository.deleteById(4) >> { throw new RuntimeException("Element not found")}

        when:
        carService.deleteCar(4)

        then:
        thrown(ResponseStatusException)
    }

    def testMergeCar(){
        given:
        def expectedCar = getCarOuptut()
        def mockCar = getMockCar()
        carRepository.save(mockCar) >> mockCar

        when:
        def actualCar = carService.mergeCar(expectedCar)
\

        then:
        actualCar == expectedCar
    }

    def testMergeCarNegative(){
        given:
        def expectedCar = getCarOuptut()

        when:
        def actualCar = carService.mergeCar(expectedCar)

        then:
        actualCar == null
        thrown(ResponseStatusException)
    }

    def getCarOuptut(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 4
        car.color= "blue"
        car.miles=134.25
        car.vin=1246
        car.year = 2012

        model.model_id = 1
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }
    def getMockCar(){
        Car car = new Car()
        Model model = new Model()
        Manufacturer manufacturer = new Manufacturer()
        car.ID = 4
        car.color= "blue"
        car.miles=134.25
        car.vin=1246
        car.year = 2012

        model.model_id = 1
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    def getCarOuptuts(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 4
        car.color= "blue"
        car.miles=134.25
        car.vin=1246
        car.year = 2012

        model.model_id = 1
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        CarDto car1 = new CarDto()
        ModelDto model1 = new ModelDto()
        ManufacturerDto manufacturer1 = new ManufacturerDto()
        car1.ID = 4
        car1.color= "black"
        car1.miles=149.55
        car1.vin=123
        car1.year = 2009

        model1.model_id = 1
        model1.model="Tribber"

        manufacturer1.manufacturer_id=1
        manufacturer1.manufacturer="Renault"

        model1.manufacturer= manufacturer1
        car1.model= model1

        List<CarDto> cars = new ArrayList<CarDto>()
        cars.add(car)
        cars.add(car1)
        return cars
    }
    def getMockCars(){
        Car car = new Car()
        Model model = new Model()
        Manufacturer manufacturer = new Manufacturer()
        car.ID = 4
        car.color= "blue"
        car.miles=134.25
        car.vin=1246
        car.year = 2012

        model.model_id = 1
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        Car car1 = new Car()
        Model model1 = new Model()
        Manufacturer manufacturer1 = new Manufacturer()
        car1.ID = 4
        car1.color= "black"
        car1.miles=149.55
        car1.vin=123
        car1.year = 2009

        model1.model_id = 1
        model1.model="Tribber"

        manufacturer1.manufacturer_id=1
        manufacturer1.manufacturer="Renault"

        model1.manufacturer= manufacturer1
        car1.model= model1

        List<CarDto> cars = new ArrayList<CarDto>()
        cars.add(car)
        cars.add(car1)
        return cars
    }
}
