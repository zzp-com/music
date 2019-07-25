package com.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.music.dao.UserDao;
import com.music.util.DBConnection;


public class TestC3p0 {
	
	@Test
	public void m01() throws SQLException {
		Connection con=DBConnection.getConnection();
		DatabaseMetaData database=con.getMetaData();
		String version=database.getDatabaseProductVersion();
		System.out.println(version);
		DBConnection.closeConnection(con);
				
	}
	
	@Test
	public void m02() throws SQLException {
		UserDao userdao=new UserDao();
		List list=userdao.getUserInfo(2);
		System.err.println(list);
		
	}
}
