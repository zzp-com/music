package com.music.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import sun.security.jca.GetInstance;

/**
 * 利用c3p0获取数据库
 * @author Administrator
 *
 */
public class DBConnection {
	private static DBConnection db=null;
	private static DataSource ds=null;
	public DBConnection() {
		if(ds == null) {
			ds= new ComboPooledDataSource();//初始化数据库
			
		}
	}
	private static DBConnection getInstance() {
		if(db == null) {
			db=new DBConnection();
		}
		return db;
		
	}
	
	private DataSource getDataSource() {
		return ds;
	}
	
	public synchronized static Connection getConnection() throws SQLException {
		
		return getInstance().getDataSource().getConnection();
		
	}
	public synchronized static void closeConnection(Connection con) throws SQLException {
		if(con !=null && !con.isClosed()) {
			con.close();
			con=null;
		}
	}
	
}
