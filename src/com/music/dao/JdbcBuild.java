package com.music.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.music.bean.AdminBean;
import com.music.util.DBConnection;
/**
 * 对dbutil组件进行简单封装，为了更加方便开发
 * @author Administrator
 *
 */
public  abstract class JdbcBuild {
	List<Map<String,Object>> getList(String sql,Object...param) throws SQLException{
		Connection con=DBConnection.getConnection();
		QueryRunner queryRunner=new QueryRunner();
		List<Map<String,Object>> list=queryRunner.query(con, sql, new MapListHandler(),param);
		DBConnection.closeConnection(con);
		return list;
	}
	
	/**
	 * 封装更新，删除，
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	int updateAll(String sql,Object...params) throws SQLException {
		Connection con=DBConnection.getConnection();
		QueryRunner queryRunner=new QueryRunner();
		//System.out.println(sql+" "+params);
		int i=queryRunner.update(con, sql,params);
		DBConnection.closeConnection(con);
		return i;
		
	}
	
	Map<String,Object> getForMap(String sql,Object...params) throws SQLException{
		Connection con=DBConnection.getConnection();
		QueryRunner queryRunner=new QueryRunner();
		//System.out.println(sql+" "+params);
		Map<String,Object> map=queryRunner.query(con, sql, new MapHandler(),params);
		DBConnection.closeConnection(con);
		return map;
	}
	
	List<AdminBean> getForBean(String sql,Object...params) throws SQLException{
		Connection con=DBConnection.getConnection();
		QueryRunner queryRunner=new QueryRunner();
		List<AdminBean> list=queryRunner.query(con, sql, new BeanListHandler(AdminBean.class),params);
		DBConnection.closeConnection(con);
		return list;
	}
	
}
