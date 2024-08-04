package com.eventTracking.Task5.business.abstracts;

import java.util.List;

import com.eventTracking.Task5.business.dto.categoryDto;
import com.eventTracking.Task5.model.Category;

public interface CategoryService {
	void createCategory(categoryDto categoryDto); 
	void updateCategory(Long id , categoryDto  categoryDto);
	void deleteCategory(long id);
	
	List<Category> getAllCategories();
}
