package com.casestudy.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long ID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model_id", referencedColumnName = "model_id")
	Model model;

	Integer year;

	@Column(name = "color")
	String color;

	@Column(name = "vin")
	Integer vin;

	@Column(name = "miles")
	Double miles;

}
