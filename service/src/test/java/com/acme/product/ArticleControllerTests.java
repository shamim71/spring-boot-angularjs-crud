package com.acme.product;

import static com.acme.product.IntegrationTestUtil.convertObjectToJsonBytes;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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


@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArticleService articleService;

	/**
	 * Unit test for Add new article
	 * 
	 * @throws Exception
	 */
	@Test
	public void addArticle() throws Exception {

	    	Article article1 = createSampleArticle();

	    	article1.setId(1000L);

		when(articleService.add(article1)).thenReturn(article1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/articles")
			.accept(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(article1))
			.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}
	private Article createSampleArticle() {
		Article article = new Article();
		article.setName("Cell Phone");
		article.setDescription("Phone");
		article.setCategory("Device");
		return article;
	}
}
