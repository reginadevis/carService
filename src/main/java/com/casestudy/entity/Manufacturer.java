package com.casestudy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "manufacturer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long manufacturer_id;

	@Column(name = "manufacturer")
	String manufacturer;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", insertable = false, updatable = false)
	List<Model> model;

}
