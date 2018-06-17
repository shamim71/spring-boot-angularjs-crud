package com.acme.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.product.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategory(String category);
}
