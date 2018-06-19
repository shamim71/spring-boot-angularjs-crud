package com.acme.product.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.product.domain.Article;
import com.acme.product.exception.ResourceNotFoundException;
import com.acme.product.repository.ArticleRepository;
import com.acme.product.service.ArticleService;

@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Article add(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Article update(Long id, Article articleReq) {
		return articleRepository.findById(id).map(article -> {
			article.setName(articleReq.getName());
			article.setDescription(articleReq.getDescription());
			article.setCategory(articleReq.getCategory());
			return articleRepository.save(article);
		}).orElseThrow(() -> new ResourceNotFoundException("Resource missing with id " + id));
	}

	@Override
	public ResponseEntity<?> delete(Long id) {

		return articleRepository.findById(id).map(article -> {
			articleRepository.delete(article);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Resource missing with id " + id));
	}

	@Override
	public List<Article> getAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article getArticle(Long id) {
		return articleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource missing with id " + id));
	}

}
