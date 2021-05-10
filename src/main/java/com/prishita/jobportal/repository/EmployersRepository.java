package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Employers;
import com.prishita.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployersRepository extends JpaRepository<Employers, Long> {

	Employers findEmployeesByUser(User user);
}
