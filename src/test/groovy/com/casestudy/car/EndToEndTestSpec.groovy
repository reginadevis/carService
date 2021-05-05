package com.casestudy.car


import com.casestudy.dto.CarDto
import com.casestudy.dto.ManufacturerDto
import com.casestudy.dto.ModelDto
import com.casestudy.repository.CarRepository
import com.casestudy.service.CarService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import java.time.Instant

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration

@SpringBootTest
@AutoConfigureMockMvc
class EndToEndTestSpec extends Specification {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    MockMvc mockMvc

    @Autowired
    CarRepository carRepository

    @Autowired
    CarService carService



    @Autowired
    WebApplicationContext context

    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    def 'testgetAllCars'() {

        given:

        when: 'get all cars'

        mockMvc.perform(MockMvcRequestBuilders.get(URI.create("/cars")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("testGetAllCars"))
        then:
        noExceptionThrown()
    }

    def 'testgetAllCarsNegative'() {

        given:

        when: 'get all cars'

        mockMvc.perform(MockMvcRequestBuilders.get(URI.create("/cars")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("testGetAllCarsNegative"))

        then:
        noExceptionThrown()
    }

    def 'testgetCar'() {

        given:

        when: 'getCar for Id 1'

        mockMvc.perform(MockMvcRequestBuilders.get(URI.create("/car/1")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("testGetCar"))

        then:
        noExceptionThrown()
    }

    def 'testgetCarNegative'() {

        given:

        when: 'getCar for Id 5'

        mockMvc.perform(MockMvcRequestBuilders.get(URI.create("/car/5")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(document("testGetCarNegative"))

        then:
        noExceptionThrown()
    }

    def 'testdeleteCar'() {

        given:

        when: 'deleteCar for Id 2'

        mockMvc.perform(MockMvcRequestBuilders.delete(URI.create("/car/2")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("testdeleteCar"))

        then:
        noExceptionThrown()
    }

    def 'testdeleteCarNegative'() {

        given:

        when: 'deleteCar for Id 6'

        mockMvc.perform(MockMvcRequestBuilders.delete(URI.create("/car/6")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(document("testDeleteCarNegative"))

        then:
        noExceptionThrown()
    }

    def 'testsaveCar'() {

        given:
        def saveCar = getCarDto()
        def savedCar = getCarDtoValid()

        when: 'saveCar'

        mockMvc.perform(MockMvcRequestBuilders.post(URI.create("/car"))
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(saveCar)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(savedCar)))
                .andDo(document("testSaveCar"))

        then:
        noExceptionThrown()
    }

    def 'testsaveCarNegative'() {

        given:
        def saveCar = getDuplicateCarDto()

        when: 'saveCar with duplicate vin'

        mockMvc.perform(MockMvcRequestBuilders.post(URI.create("/car"))
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(saveCar)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Duplicate entry of car"))
                .andDo(document("testSaveCarNegative"))

        then:
        noExceptionThrown()
    }

    def 'testupdateCar'() {

        given:
        def updateCar = getUpdateCarDto()
        def updatedCar = getUpdateCarDtoValid()

        when: 'updateCar'

        mockMvc.perform(MockMvcRequestBuilders.put(URI.create("/car"))
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(updateCar)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(updatedCar)))
                .andDo(document("testUpdateCar"))

        then:
        noExceptionThrown()
    }
    def 'testupdateCarNegative'() {

        given:
        def updateCar = getInvalidUpdateCarDto()

        when: 'updateCar with invalid json'

        mockMvc.perform(MockMvcRequestBuilders.put(URI.create("/car"))
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(updateCar)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(document("testUpdateCarNegative"))

        then:
        noExceptionThrown()
    }

    def getCarDto(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 3
        car.color= "blue"
        car.miles=134.25
        car.vin=1113
        car.year = 2012

        model.model_id = 3
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    def getCarDtoValid(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 3
        car.color= "blue"
        car.miles=134.25
        car.vin=1113
        car.year = 2012

        model.model_id = 3
        model.model="Duster"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Renault"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    def getUpdateCarDto(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 3
        car.color= "black"
        car.miles=200
        car.vin=1113
        car.year = 2012

        model.model_id = 3
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    def getUpdateCarDtoValid(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 3
        car.color= "black"
        car.miles=200
        car.vin=1113
        car.year = 2012

        model.model_id = 3
        model.model="Duster"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Renault"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    def getDuplicateCarDto(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.color= "blue"
        car.miles=134.25
        car.vin=1113
        car.year = 2012

        model.model_id = 3
        model.model="Duster"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Renault"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    def getInvalidUpdateCarDto(){
        CarDto car = new CarDto()
        ModelDto model = new ModelDto()
        ManufacturerDto manufacturer = new ManufacturerDto()
        car.ID = 3
        car.color= "black"
        car.miles=100.00
        car.vin=1113
        car.year = 2012

        model.model_id = 7
        model.model="Figo"

        manufacturer.manufacturer_id=1
        manufacturer.manufacturer="Ford"

        model.manufacturer= manufacturer
        car.model= model

        return car
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void prettyPrint() {

        System.out.println("Time is "+ Instant.now())
    }
}
