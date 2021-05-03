package com.casestudy.validator;

import com.casestudy.dto.ModelDto;
import com.casestudy.entity.Manufacturer;
import com.casestudy.entity.Model;
import com.casestudy.repository.ManufacturerRepository;
import com.casestudy.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;

public class CarModelValidator implements ConstraintValidator<ValidCarModel, ModelDto> {
	@Autowired
	Validator validator;

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	@Override
	public boolean isValid(ModelDto modelDto, ConstraintValidatorContext constraintValidatorContext) {

		boolean isValid = true;
		Model dbModel = null;
		Manufacturer manufacturer = null;

		try {
			dbModel = modelRepository.getById(modelDto.getModel_id());

			manufacturer = manufacturerRepository.getById(modelDto.getManufacturer().getManufacturer_id());
			
			System.out.println(dbModel.getModel());
			System.out.println(manufacturer.getManufacturer());
		} catch (EntityNotFoundException e) {
			isValid = false;
		}
		if (isValid && !dbModel.getManufacturer().equals(manufacturer)) {
			isValid = false;
		}

		System.out.println("isValid " + isValid);
		return isValid;
	}

}
