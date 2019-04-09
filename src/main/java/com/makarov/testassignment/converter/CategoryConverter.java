package com.makarov.testassignment.converter;

import com.makarov.testassignment.dto.CategoryDto;
import com.makarov.testassignment.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public List<CategoryDto> toDtoList(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryDtoList.add(toDto(category));
        }
        return categoryDtoList;
    }

    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    public List<Category> toEntityList(List<CategoryDto> categoryDtoList) {
        List<Category> categoryList = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtoList) {
            categoryList.add(toEntity(categoryDto));
        }
        return categoryList;
    }
}
