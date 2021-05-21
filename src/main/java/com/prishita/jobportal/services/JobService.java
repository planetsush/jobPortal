package com.prishita.jobportal.services;

import com.prishita.jobportal.entity.Applicants;
import com.prishita.jobportal.entity.Jobs;
import com.prishita.jobportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class JobService {

	@Autowired
	private JobsRepository jobsRepository;

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployersRepository employersRepository;

	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private ApplicantsRepository applicantsRepository;

	public Jobs createJob(Jobs job, Principal principal, String category) {
		job.setActive(false);
		job.setCategories(categoriesRepository.findCategoriesByName(category));
		job.setCreatedBy(employersRepository.findEmployeesByUser(userRepository.findUserByEmail(principal.getName()).get()));
		return jobsRepository.save(job);
	}

	public Jobs verifyJob(Long id, String action) {
		Optional<Jobs> jobOptional = jobsRepository.findById(id);
		if (jobOptional.isEmpty()) {
			return null;
		}
		Jobs job = jobOptional.get();

		job.setActive(action.equals("verified"));

		return jobsRepository.save(job);
	}

	public Applicants applyJob(Long id, String name) {
		Applicants applicant = new Applicants();
		applicant.setJob(jobsRepository.getOne(id));
		applicant.setEmployee(employeesRepository.findEmployeesByUser(userRepository.findUserByEmail(name).get()));
		return applicantsRepository.save(applicant);
	}
}
