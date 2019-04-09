package com.makarov.testassignment.converter;

import com.makarov.testassignment.dto.NewsDto;
import com.makarov.testassignment.entity.News;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class NewsConverterTest {

    private NewsConverter newsConverter;

    @Mock
    private CategoryConverter categoryConverter;

    @Before
    public void setUp() {
        this.newsConverter = new NewsConverter(categoryConverter);
    }

    @Test
    public void testToEntityReturnNotNull() {
        NewsDto newsDto = generateNewsDto(1);

        News actualNews = newsConverter.toEntity(newsDto);

        assertNotNull(actualNews);
    }

    @Test
    public void testToDtoReturnNotNull() {
        News news = generateNews(1);

        NewsDto actualNews = newsConverter.toDto(news);

        assertNotNull(actualNews);
    }

    @Test
    public void testToEntityReturnCorrectValues() {
        NewsDto newsDto = generateNewsDto(1);
        News expectedNews = generateNews(1);

        News actualNews = newsConverter.toEntity(newsDto);

        assertEquals(expectedNews.getId(), actualNews.getId());
        assertEquals(expectedNews.getTitle(), actualNews.getTitle());
        assertEquals(expectedNews.getContent(), actualNews.getContent());
    }

    @Test
    public void testToDtoReturnCorrectValue() {
        News news = generateNews(1);
        NewsDto expectedNews = generateNewsDto(1);

        NewsDto actualNews = newsConverter.toDto(news);

        assertEquals(expectedNews.getId(), actualNews.getId());
        assertEquals(expectedNews.getTitle(), actualNews.getTitle());
        assertEquals(expectedNews.getContent(), actualNews.getContent());
    }

    @Test
    public void testToEntityListReturnNotNull() {
        List<NewsDto> newsDtoList = generateNewsDtoList();

        List<News> actualNewsList = newsConverter.toEntityList(newsDtoList);

        assertNotNull(actualNewsList);
    }

    @Test
    public void testToDtoListReturnNotNull() {
        List<News> newsList = generateNewsList();

        List<NewsDto> actualNewsList = newsConverter.toDtoList(newsList);

        assertNotNull(actualNewsList);
    }

    private List<NewsDto> generateNewsDtoList() {
        List<NewsDto> newsDtoList = new ArrayList<>();

        for (int i = 0; i <= 3; i++) {
            newsDtoList.add(generateNewsDto(i));
        }
        return newsDtoList;
    }

    private List<News> generateNewsList() {
        List<News> newsList = new ArrayList<>();

        for (int i = 0; i <= 3; i++) {
            newsList.add(generateNews(i));
        }
        return newsList;
    }

    private News generateNews(long id) {
        News news = new News();

        news.setId(id);
        news.setTitle("title" + id);
        news.setContent("content" + id);
        news.setPublicationDate(new Date());
        return news;
    }

    private NewsDto generateNewsDto(long id) {
        NewsDto newsDto = new NewsDto();

        newsDto.setId(id);
        newsDto.setTitle("title" + id);
        newsDto.setContent("content" + id);
        return newsDto;
    }
}
