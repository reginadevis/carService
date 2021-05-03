package com.casestudy.dto;

import lombok.*;

@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDto {

	Long manufacturer_id;

	String manufacturer;
}
