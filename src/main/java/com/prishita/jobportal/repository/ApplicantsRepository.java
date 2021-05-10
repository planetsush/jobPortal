package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Applicants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantsRepository extends JpaRepository<Applicants, Long> {
}
