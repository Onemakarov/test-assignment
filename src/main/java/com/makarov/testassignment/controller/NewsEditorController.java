package com.makarov.testassignment.controller;

import com.makarov.testassignment.converter.CategoryConverter;
import com.makarov.testassignment.converter.NewsConverter;
import com.makarov.testassignment.dto.CategoryDto;
import com.makarov.testassignment.dto.NewsDto;
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
public class NewsEditorController {

    private NewsService newsService;

    private CategoryService categoryService;

    private NewsConverter newsConverter;

    private CategoryConverter categoryConverter;

    @Autowired
    public NewsEditorController(NewsService newsService, CategoryService categoryService, NewsConverter newsConverter, CategoryConverter categoryConverter) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.newsConverter = newsConverter;
        this.categoryConverter = categoryConverter;
    }

    @GetMapping("/editor")
    public ModelAndView getEditorForCreate() {
        List<CategoryDto> categoryList = categoryConverter.toDtoList(categoryService.getAllCategories());
        NewsDto news = new NewsDto();
        news.setCategoryList(categoryList);
        ModelAndView model = new ModelAndView();

        model.setViewName("editor");
        model.addObject("categoryList", categoryList);
        model.addObject("search", new SearchDto());
        model.addObject("news", news);
        return model;
    }

    @GetMapping("/editor/{newsId}")
    public ModelAndView getEditorForEdit(@PathVariable("newsId") long newsId) {
        NewsDto newsDto = newsConverter.toDto(newsService.getNewsById(newsId));
        for (CategoryDto categoryDto : newsDto.getCategoryList()) {
            categoryDto.setChecked(true);
        }
        List<CategoryDto> categoryDtoList = categoryConverter.toDtoList(categoryService.getAllCategories());
        setCheckedCategories(newsDto, categoryDtoList);
        newsDto.setCategoryList(categoryDtoList);
        ModelAndView model = new ModelAndView();

        model.setViewName("editor");
        model.addObject("categoryList", categoryDtoList);
        model.addObject("search", new SearchDto());
        model.addObject("news", newsDto);
        return model;
    }

    @PostMapping("/editor/{newsId}")
    public String saveChanges(@PathVariable("newsId") long newsId,
                                     @ModelAttribute NewsDto newsDto) {
        newsDto.setId(newsId);
        newsService.updateNews(newsConverter.toEntity(newsDto));
        return "redirect:/";
    }

    @PostMapping("/editor")
    public String createNews(@ModelAttribute NewsDto newsDto) {
        newsService.saveNews(newsConverter.toEntity(newsDto));
        return "redirect:/";
    }

    private void setCheckedCategories(NewsDto newsDto, List<CategoryDto> categoryDtoList) {
        for (CategoryDto categoryDto : categoryDtoList) {
            for (CategoryDto categoryDtoFromNews : newsDto.getCategoryList()) {
                if (categoryDto.getId() == categoryDtoFromNews.getId()) {
                    categoryDto.setChecked(true);
                }
            }
        }
    }
}