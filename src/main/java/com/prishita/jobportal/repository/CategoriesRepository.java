package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

	Categories findCategoriesByName(String name);
}
