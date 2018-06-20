package com.acme.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.acme.product.controller.ArticleController;
import com.acme.product.domain.Article;
import com.acme.product.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticleService articleService;

	private final ObjectMapper mapper = new ObjectMapper();
	private static final String API_URL = "/api/articles";

	/**
	 * Unit test for Add new article
	 * 
	 * @throws Exception
	 */
	@Test
	public void addArticle() throws Exception {

		Article article1 = createSampleArticle();

		given(articleService.add(any(Article.class))).willReturn(article1);
		String res = mockMvc
				.perform(post(API_URL).content(mapper.writeValueAsString(article1)).contentType(APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		Article article = mapper.readValue(res, Article.class);
		assertEquals(article1, article);

	}

	@Test
	public void updateArticle() throws Exception {

		Article article1 = createSampleArticle();

		given(articleService.update(any(Long.class), any(Article.class))).willReturn(article1);
		String res = mockMvc
				.perform(put(API_URL + "/100").content(mapper.writeValueAsString(article1))
						.contentType(APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Article article = mapper.readValue(res, Article.class);
		assertEquals(article1, article);

	}
	@Test
	public void deleteArticle() throws Exception {


		
		MockHttpServletResponse response = mockMvc.perform(delete(API_URL + "/100"))
				.andDo(print()).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());

	}
	@Test
	public void getArticle() throws Exception {

		Article article = createSampleArticle();

		given(articleService.getArticle(any(Long.class))).willReturn(article);

		MockHttpServletResponse response = mockMvc.perform(get(API_URL + "/100").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		String res = response.getContentAsString();

		Article resArticle = mapper.readValue(res, Article.class);
		assertEquals(resArticle, article);

	}

	@Test
	public void getArticleList() throws Exception {

		MockHttpServletResponse response = mockMvc.perform(get(API_URL).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	private Article createSampleArticle() {
		Article article = new Article();
		article.setName("Cell Phone");
		article.setDescription("Phone");
		article.setCategory("Device");
		article.setId(1000L);

		return article;
	}
}
