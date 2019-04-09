package com.makarov.testassignment.dao;

import com.makarov.testassignment.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CategoryDao {

    private final EntityManager entityManager;

    @Autowired
    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveCategory(Category category) {
        entityManager.persist(category);
    }

    public List<Category> getAllCategories() {
        String query = "SELECT c FROM Category c";
        return entityManager.createQuery(query, Category.class).getResultList();
    }

    public Category getCategoryById(long categoryId) {
        return entityManager.find(Category.class, categoryId);
    }

    public void deleteCategoryById(long categoryId) {
        String query = "DELETE FROM Category c WHERE c.id = :categoryId";
        entityManager.createQuery(query, Category.class)
                .setParameter("categoryId", categoryId)
                .executeUpdate();
    }
}
