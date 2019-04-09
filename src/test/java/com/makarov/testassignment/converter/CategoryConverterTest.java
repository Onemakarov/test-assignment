package com.makarov.testassignment.converter;

import com.makarov.testassignment.dto.CategoryDto;
import com.makarov.testassignment.entity.Category;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CategoryConverterTest {

    private CategoryConverter categoryConverter = new CategoryConverter();

    @Test
    public void testToEntityReturnNotNull() {
        CategoryDto categoryDto = generateCategoryDto(1);

        Category actualCategory = categoryConverter.toEntity(categoryDto);

        assertNotNull(actualCategory);
    }

    @Test
    public void testToEntityListReturnNotNull() {
        List<CategoryDto> categoryDtoList = generateCategoryDtoList();

        List<Category> actualCategoryList = categoryConverter.toEntityList(categoryDtoList);

        assertNotNull(actualCategoryList);
    }

    @Test
    public void testToEntityReturnCorrectValues() {
        CategoryDto categoryDto = generateCategoryDto(1);
        Category expectedCategory = generateCategory(1);

        Category actualCategory = categoryConverter.toEntity(categoryDto);

        assertEquals(expectedCategory.getId(), actualCategory.getId());
        assertEquals(expectedCategory.getName(), actualCategory.getName());
    }

    @Test
    public void testToDtoReturnNotNull() {
        Category category = generateCategory(1);

        CategoryDto actualCategoryDto = generateCategoryDto(1);

        assertNotNull(actualCategoryDto);
    }

    @Test
    public void testToDtoListReturnNotNull() {
        List<Category> categoryList = generateCategoryList();

        List<CategoryDto> actualCategoryList = categoryConverter.toDtoList(categoryList);

        assertNotNull(actualCategoryList);
    }

    @Test
    public void testToDtoReturnCorrectValues() {
        Category category = generateCategory(1);
        CategoryDto expectedCategory = generateCategoryDto(1);

        CategoryDto actualCategory = categoryConverter.toDto(category);

        assertEquals(expectedCategory.getId(), actualCategory.getId());
        assertEquals(expectedCategory.getName(), actualCategory.getName());
    }

    private Category generateCategory(long i) {
        Category category = new Category();

        category.setId(i);
        category.setName("category" + i);
        return category;
    }

    private List<Category> generateCategoryList() {
        List<Category> categoryList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            categoryList.add(generateCategory(i));
        }
        return categoryList;
    }

    private CategoryDto generateCategoryDto(long id) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(id);
        categoryDto.setName("category" + id);
        categoryDto.setChecked(true);
        return categoryDto;
    }

    private List<CategoryDto> generateCategoryDtoList() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            categoryDtoList.add(generateCategoryDto(i));
        }
        return categoryDtoList;
    }
}
