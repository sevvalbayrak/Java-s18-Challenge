package com.workintech.jpa.onetomany.service;

import com.workintech.jpa.onetomany.entity.Category;
import com.workintech.jpa.onetomany.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }



    @Override
    public Category findById(int id) {
        Optional <Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        return null;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}