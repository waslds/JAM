package com.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCInsertTest {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		
		String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		
		String sql = "INSERT INTO article";
		sql += " SET regDate = NOW()";
		sql += ", updateDate = NOW()";
		sql += ", title = '제목1'";
		sql += ", `body` = '내용1'";
		
		try (Connection conn = DriverManager.getConnection(url, "root", "1234");
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.executeUpdate();
		}
		
		catch (SQLException e) {
			System.out.println("에러: " + e);
		}
	}
}
