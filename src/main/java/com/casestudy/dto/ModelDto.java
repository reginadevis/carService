package com.casestudy.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {

	@NotNull
	Long model_id;

	String model;

	ManufacturerDto manufacturer;
}
