package com.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.JAM.dao.ArticleDao;
import com.example.JAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		
		return articleDao.doWrite(title, body);
	}

	public List<Article> selectList() {
		List<Map<String, Object>> articleMaps = articleDao.selectList();
		
		List<Article> articles = new ArrayList<>();
		for (Map<String, Object> articleMap : articleMaps) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public Article selectDetail(int id) {
		Map<String, Object> articleMap = articleDao.selectDetail(id);
		
		if (articleMap.isEmpty()) {
			return null;
		}
		
		return new Article(articleMap);
	}
	
	public int getNumInCmd(String cmd) {
		return Integer.parseInt(cmd.split(" ")[2]);
	}

	public int isExistArticle(int id) {
		return articleDao.isExistArticle(id);
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id, title, body);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}
}
