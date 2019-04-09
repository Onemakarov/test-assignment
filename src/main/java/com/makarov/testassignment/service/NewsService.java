package com.makarov.testassignment.service;

import com.makarov.testassignment.dao.NewsDao;
import com.makarov.testassignment.entity.Category;
import com.makarov.testassignment.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    private CategoryService categoryService;

    private NewsDao newsDao;

    @Autowired
    public NewsService(CategoryService categoryService, NewsDao newsDao) {
        this.categoryService = categoryService;
        this.newsDao = newsDao;
    }

    @Transactional
    public void saveNews(News news) {
        news.setPublicationDate(new Date());
        setCategoriesInNewsFromDb(news);
        newsDao.saveNews(news);
    }

    @Transactional
    public List<News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Transactional
    public News getNewsById(long newsId) {
        return newsDao.getNewsById(newsId);
    }

    @Transactional
    public List<News> searchNewsByCategoryName(String categoryName) {
        return newsDao.searchNewsByCategoryName(categoryName);
    }

    @Transactional
    public List<News> searchNewsByTitleAndContent(String searchQuery) {
        return newsDao.searchByTitleAndContent(searchQuery);
    }

    private void setCategoriesInNewsFromDb(News news) {
        List<Category> categoryListFromDb = categoryService.getAllCategories();
        List<Category> categoryListForNews = new ArrayList<>();
        for (Category categoryFromNews : news.getCategoryList()) {
            for (Category categoryFromDb : categoryListFromDb) {
                if (categoryFromNews.getId() == categoryFromDb.getId()) {
                    categoryListForNews.add(categoryFromDb);
                }
            }
        }
        news.setCategoryList(categoryListForNews);
    }

    @Transactional
    public void deleteNewsById(long newsId) {
        newsDao.deleteNewsById(newsId);
    }

    @Transactional
    public void updateNews(News news) {
        news.setPublicationDate(new Date());
        setCategoriesInNewsFromDb(news);
        newsDao.updateNews(news);
    }
}
