package com.acme.product;

import static com.acme.product.IntegrationTestUtil.convertObjectToJsonBytes;

import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.acme.product.controller.ArticleController;
import com.acme.product.domain.Article;
import com.acme.product.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticleService articleService;

	 private final ObjectMapper mapper = new ObjectMapper();
	 
	/**
	 * Unit test for Add new article
	 * 
	 * @throws Exception
	 */
	@Test
	public void addArticle() throws Exception {

	    	Article article1 = createSampleArticle();
	    	article1.setId(1000L);

		given(articleService.add(any(Article.class))).willReturn(article1);
		String res = mockMvc.perform(post("/articles")
		            .content(mapper.writeValueAsString(article1))
		            .contentType(APPLICATION_JSON)
		        ).andDo(print())
		            .andExpect(status().isCreated())
		            .andReturn()
		            .getResponse()
		            .getContentAsString();
		
		Article article = mapper.readValue(res, Article.class);
		        assertEquals(article1, article);
		        

	}
	private Article createSampleArticle() {
		Article article = new Article();
		article.setName("Cell Phone");
		article.setDescription("Phone");
		article.setCategory("Device");
		return article;
	}
}
