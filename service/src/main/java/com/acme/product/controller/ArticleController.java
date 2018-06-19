package com.acme.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.product.domain.Article;
import com.acme.product.service.ArticleService;

@RestController
@RequestMapping(path = "/api")
public class ArticleController extends AbstractWSController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/articles")
	public List<Article> getArticles() {
		return articleService.getAll();
	}

	@GetMapping("/articles/{id}")
	public Article getArticleById(@PathVariable Long id) {
		return articleService.getArticle(id);
	}

	@PostMapping(path = "/articles")
	public ResponseEntity<?> addArticle(@RequestBody Article article) {
		
		Article a = articleService.add(article);
		return new ResponseEntity<Article>(a, appendLocationHeader(a.getId()), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/articles/{id}")
	public ResponseEntity<?> updateArticle(@PathVariable Long id,@RequestBody Article article) {
		
		Article updatedArticle = articleService.update(id, article);
		return new ResponseEntity<Article>(updatedArticle, HttpStatus.OK);
	}
	
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<?> deleteArticleById(@PathVariable Long id) {
		articleService.delete(id);
		return ResponseEntity.ok().build();
	}
}
