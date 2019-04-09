package com.makarov.testassignment.controller;

import com.makarov.testassignment.converter.CategoryConverter;
import com.makarov.testassignment.converter.NewsConverter;
import com.makarov.testassignment.dto.SearchDto;
import com.makarov.testassignment.entity.Category;
import com.makarov.testassignment.entity.News;
import com.makarov.testassignment.service.CategoryService;
import com.makarov.testassignment.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    private NewsService newsService;

    private CategoryService categoryService;

    private NewsConverter newsConverter;

    private CategoryConverter categoryConverter;

    @Autowired
    public SearchController(NewsService newsService, CategoryService categoryService, NewsConverter newsConverter, CategoryConverter categoryConverter) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.newsConverter = newsConverter;
        this.categoryConverter = categoryConverter;
    }

    @GetMapping("categories/{categoryName}")
    public ModelAndView searchNewsByCategory(@PathVariable("categoryName") String categoryName) {
        List<News> newsList = newsService.searchNewsByCategoryName(categoryName);
        List<Category> categoryList = categoryService.getAllCategories();
        ModelAndView model = new ModelAndView();

        model.setViewName("newsList");
        model.addObject("newsList", newsConverter.toDtoList(newsList));
        model.addObject("categoryList", categoryConverter.toDtoList(categoryList));
        model.addObject("search", new SearchDto());
        return model;
    }

    @PostMapping("/search")
    public ModelAndView searchNews(@ModelAttribute SearchDto searchDto) {
        List<News> newsByTitleAndContent = newsService.searchNewsByTitleAndContent(searchDto.getSearchQuery());
        List<Category> categoryList = categoryService.getAllCategories();
        ModelAndView model = new ModelAndView();
        model.setViewName("newsList");
        model.addObject("categoryList", categoryConverter.toDtoList(categoryList));
        model.addObject("newsList", newsConverter.toDtoList(newsByTitleAndContent));
        model.addObject("search", new SearchDto());
        return model;
    }
}
