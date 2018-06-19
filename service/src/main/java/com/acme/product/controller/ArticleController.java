package com.acme.product.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.acme.product.domain.Article;
import com.acme.product.service.ArticleService;

@RestController
public class ArticleController extends AbstractWSController{

    private final  ArticleService articleService;
 

    public ArticleController(ArticleService service) {
        this.articleService = service;
    }
    
    @GetMapping("/articles")
    public List<Article> getArticles(){
	return articleService.getAll();
    }
    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable Long id){
	return articleService.getArticle(id);
    }
    
    @PostMapping(path="/articles")
    public ResponseEntity<?> addArticle(@RequestBody Article article) {
	Article a = articleService.add(article);
	UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}");
	URI location = builder.buildAndExpand(a.getId()).toUri();
	
	HttpHeaders headers = new HttpHeaders();
	headers.add("Location", location.getPath());
	return new ResponseEntity<Article>(a,headers,HttpStatus.CREATED);
	//return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?>  deleteArticleById(@PathVariable Long id){
	 articleService.delete(id);
	 return ResponseEntity.ok().build();
    }
}
