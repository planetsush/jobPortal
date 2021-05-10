package com.prishita.jobportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jobs implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Employers createdBy;

	@ManyToOne
	private Categories categories;

	private String title;

	private String hours;

	private String salary;

	private String experience;

	private String description;

	private String city;

	private Boolean active;
}
