package com.makarov.testassignment.service;

import com.makarov.testassignment.dao.CategoryDao;
import com.makarov.testassignment.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    public void saveCategory(Category category) {
        categoryDao.saveCategory(category);
    }

    @Transactional
    public void deleteCategoryById(long categoryId) {
        categoryDao.deleteCategoryById(categoryId);
    }

    @Transactional
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
