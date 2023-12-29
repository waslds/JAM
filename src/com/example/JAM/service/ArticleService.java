package com.example.JAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.example.JAM.dao.ArticleDao;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		
		return articleDao.doWrite(title, body);
	}

	public List<Map<String, Object>> selectList() {
		return articleDao.selectList();
	}

	public Map<String, Object> selectDetail(int id) {
		return articleDao.selectDetail(id);
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
