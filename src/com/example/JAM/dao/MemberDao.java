package com.example.JAM.dao;

import java.sql.Connection;

import com.example.JAM.util.DBUtil;
import com.example.JAM.util.SecSql;

public class MemberDao {

	private Connection conn;
	
	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public boolean isLoginIdDupChk(String loginId) {
		SecSql sql = SecSql.from("SELECT COUNT(*) > 0 FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		
		return DBUtil.selectRowBooleanValue(conn, sql);
	}

	public void doJoin(String loginId, String loginPw, String name) {
		SecSql sql = SecSql.from("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);
		
		DBUtil.insert(conn, sql);
	}
}
