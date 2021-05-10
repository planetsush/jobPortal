package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Resumes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumesRepository extends JpaRepository<Resumes, Long> {
}
