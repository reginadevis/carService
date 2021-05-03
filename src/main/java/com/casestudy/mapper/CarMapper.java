package com.casestudy.mapper;

import com.casestudy.dto.CarDto;
import com.casestudy.dto.ManufacturerDto;
import com.casestudy.dto.ModelDto;
import com.casestudy.entity.Car;
import com.casestudy.entity.Manufacturer;
import com.casestudy.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

	CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

	CarDto carToCarDto(Car car);

	Car carDtotoCar(CarDto carDto);

	List<CarDto> carListtoCarDtoList(List<Car> cars);

	ModelDto modelToModelDto(Model model);

	Model modelDtoToModel(ModelDto modelDto);

	ManufacturerDto manufacturerToManufacturerDto(Manufacturer manufacturer);

	Manufacturer manufacturerDtoToManufacturer(ManufacturerDto manufacturerDto);

}
