package com.prishita.jobportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private User user;

	private String gender;

	private String currentLocation;

	private BigDecimal annualSalary;

	private String currentIndustry;

	private String quali;

	private String profile;

	@OneToOne
	private Resumes resume;


}
