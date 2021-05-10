package com.prishita.jobportal.services;

import com.prishita.jobportal.entity.Categories;
import com.prishita.jobportal.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;

	public Categories createCategory(Categories category) {
		return categoriesRepository.save(category);
	}

	public void deleteCategory(Categories category) {
		categoriesRepository.delete(category);
	}

	public List<Categories> getAllCategories() {
		return categoriesRepository.findAll();
	}
}
