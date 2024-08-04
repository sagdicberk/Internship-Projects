package com.eventTracking.Task5.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.business.abstracts.CategoryService;
import com.eventTracking.Task5.business.dto.categoryDto;
import com.eventTracking.Task5.model.Category;
import com.eventTracking.Task5.repository.CategoryRepository;

@Service
public class CategoryManager implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
    @Override
    public void createCategory(categoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }

	@Override
	public void updateCategory(Long id, categoryDto categoryDto) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
	    category.setName(categoryDto.getName());
	    categoryRepository.save(category);
		
	}

	@Override
	public void deleteCategory(long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
		categoryRepository.deleteById(category.getId());
		
	}

	
	@Override
	public List<Category> getAllCategories() {
		List<Category> allCategories  = categoryRepository.findAll();
		return allCategories;
	}
	
	
}
