package com.acme.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.acme.product.domain.Article;

/**
 * Manage CRUD operation on article 
 * 
 * @author shamimz
 *
 */
public interface ArticleService {

    /**
     * Add a new article into Database
     * @param article
     * @return
     */
    public Article add(Article article);
    
    /**
     * Get article by ID
     * @param id
     * @return
     */
    public Article getArticle(Long id);
    
    /**
     * Update Article object
     * @param id
     * @param article
     * @return
     */
    public Article update(Long id,Article article);
    
    /**
     * Delete article object By ID
     * @param id
     * @return
     */
    public ResponseEntity<?> delete(Long id);
    
    /**
     * Load all article object
     * @return
     */
    public List<Article> getAll();
}
