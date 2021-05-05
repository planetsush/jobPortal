package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

	Optional<Authorities> findAuthoritiesByAuthority(String authority);
}
