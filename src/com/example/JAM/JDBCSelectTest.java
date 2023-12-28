package com.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.JAM.dto.Article;

public class JDBCSelectTest {
	
	public static void main(String[] args) {
		
		List<Article> articles = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		
		String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		
		String sql = "SELECT * FROM article";
		sql += " ORDER BY id DESC;";
		
		try (Connection conn = DriverManager.getConnection(url, "root", "1234");
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();) {
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String regDate = rs.getString("regDate");
				String updateDate = rs.getString("updateDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				Article article = new Article(id, regDate, updateDate, title, body);
				articles.add(article);
			}
			
			for (Article article : articles) {
				System.out.printf("%d, %s, %s, %s, %s\n", article.id, article.regDate, article.updateDate, article.title, article.body);
			}
		}
		catch (SQLException e) {
			System.out.println("에러: " + e);
		}
	}
}
