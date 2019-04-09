package com.makarov.testassignment.dao;

import com.makarov.testassignment.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class NewsDao {

    private final EntityManager entityManager;

    @Autowired
    public NewsDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveNews(News news) {
        entityManager.persist(news);
    }

    public List<News> getAllNews() {
        String query = "SELECT n FROM News n ORDER BY n.publicationDate DESC";
        return entityManager.createQuery(query, News.class).getResultList();
    }

    public News getNewsById(long newsId) {
        return entityManager.find(News.class, newsId);
    }

    public List<News> getNewsListByCategoryId(long categoryId) {
        String query = "SELECT n FROM News n " +
                "INNER JOIN news_categories nc on news.news_id = nc.news_id " +
                "WHERE category_id = :categoryId ORDER BY n.publicationDate DESC";
        return entityManager.createQuery(query, News.class).getResultList();
    }

    public void deleteNewsById(long newsId) {
        String query = "DELETE FROM News n where n.id = :newsId";
        entityManager.createQuery(query)
                .setParameter("newsId", newsId)
                .executeUpdate();
    }

    public void updateNews(News news) {
        entityManager.merge(news);
    }

    public List<News> searchNewsByCategoryName(String categoryName) {
        String query = "SELECT n FROM News n " +
                "INNER JOIN n.categoryList c WHERE " +
                "c.name = :categoryName ORDER BY n.publicationDate DESC";
        return entityManager
                .createQuery(query, News.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    public List<News> searchByTitleAndContent(String searchQuery) {
        String sqlQuery = "SELECT * FROM news n WHERE " +
                "make_tsvector(title, content) @@ plainto_tsquery('russian', :searchQuery)";
        return entityManager
                .createNativeQuery(sqlQuery, News.class)
                .setParameter("searchQuery", searchQuery)
                .getResultList();
    }
}
