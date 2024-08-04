package com.eventTracking.Task5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventTracking.Task5.model.Category;
import com.eventTracking.Task5.model.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {



}
