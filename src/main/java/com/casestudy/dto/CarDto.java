package com.casestudy.dto;

import com.casestudy.validator.ValidCarModel;
import lombok.*;

@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

	Long ID;

	@ValidCarModel
	ModelDto model;

	Integer year;

	String color;

	Integer vin;

	Double miles;

}
