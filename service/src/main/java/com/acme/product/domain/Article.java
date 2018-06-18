package com.acme.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article {

    @Id
    @GeneratedValue(generator = "article_generator")
    @SequenceGenerator(
            name = "article_generator",
            sequenceName = "article_sequence",
            initialValue = 1000
    )
    private Long id;
    
    private String name;
    
    private String description;

    private String category;
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
	return "Article [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category + "]";
    }
    
}
