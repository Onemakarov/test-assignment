package com.makarov.testassignment.converter;

import com.makarov.testassignment.dto.CategoryDto;
import com.makarov.testassignment.dto.NewsDto;
import com.makarov.testassignment.entity.Category;
import com.makarov.testassignment.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConverter {

    private CategoryConverter categoryConverter;

    private final DateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM HH:mm");

    @Autowired
    public NewsConverter(CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    public NewsDto toDto(News news) {
        NewsDto newsDto = new NewsDto();

        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setContent(news.getContent());
        newsDto.setPublicationDate(dateFormat.format(news.getPublicationDate()));
        newsDto.setCategoryList(categoryConverter.toDtoList(news.getCategoryList()));
        return newsDto;
    }

    public List<NewsDto> toDtoList(List<News> newsList) {
        List<NewsDto> newsDtoList = new ArrayList<>();

        for (News news : newsList) {
            newsDtoList.add(toDto(news));
        }
        return newsDtoList;
    }

    public News toEntity(NewsDto newsDto) {
        News news = new News();

        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        if (newsDto.getCategoryList() != null) {
            List<Category> categoryList = new ArrayList<>();
            for (CategoryDto categoryDto : newsDto.getCategoryList()) {
                if (categoryDto.isChecked()) {
                    Category category = categoryConverter.toEntity(categoryDto);
                    List<News> newsList = new ArrayList<>();
                    newsList.add(news);
                    categoryList.add(category);
                }
            }
            news.setCategoryList(categoryList);
        }
        return news;
    }

    public List<News> toEntityList(List<NewsDto> newsDtoList) {
        List<News> newsList = new ArrayList<>();

        for (NewsDto newsDto : newsDtoList) {
            newsList.add(toEntity(newsDto));
        }
        return newsList;
    }
}
