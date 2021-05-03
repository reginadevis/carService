package com.casestudy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "model")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "model_id")
	Long model_id;

	@Column(name = "model")
	String model;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "model_id", referencedColumnName = "model_id", insertable = false, updatable = false)
	List<Car> car;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", insertable = false, updatable = false)
	Manufacturer manufacturer;
}
