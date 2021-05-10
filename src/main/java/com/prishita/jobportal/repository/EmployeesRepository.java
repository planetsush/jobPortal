package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Employees;
import com.prishita.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

	Employees findEmployeesByUser(User user);
}
