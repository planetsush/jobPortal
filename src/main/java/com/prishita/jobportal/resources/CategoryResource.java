package com.prishita.jobportal.resources;

import com.prishita.jobportal.entity.Categories;
import com.prishita.jobportal.repository.CategoriesRepository;
import com.prishita.jobportal.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {

	@Autowired
	private CategoriesService categoriesService;

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping("")
	private Categories createCategory(@RequestBody Categories category) {
		return categoriesService.createCategory(category);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping("")
	private void deleteCategory(@RequestBody Categories category) {
		categoriesService.deleteCategory(category);
	}

	@GetMapping("")
	private List<Categories> getAllCategories() {
		return categoriesService.getAllCategories();
	}
}
