package com.workintech.jpa.onetomany.repository;

import com.workintech.jpa.onetomany.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
