package com.acme.product;

import static com.acme.product.IntegrationTestUtil.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.acme.product.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceApplicationTests {

    @Autowired
    private MockMvc mvc;
	 private static final String API_URL="/api/articles";
    @Test
    public void getArticleTest() throws Exception {
	this.mvc.perform(get(API_URL).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void addArticle() throws Exception {

	Article article = new Article();
	article.setName("Cell Phone");
	article.setDescription("Phone");
	article.setCategory("Device");

	this.mvc.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(convertObjectToJsonBytes(article))).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void getArticleByIdTest() throws Exception {
	this.mvc.perform(get(API_URL+"/1000").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.name").value("Hello"));
    }

}
