package com.music.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NewsDao extends JdbcBuild {
	int pageSize=6;//设置每页想要显示多少条数据
	public List<Map<String,Object>> getNewsInfo() throws SQLException{
		String sql="select NewsID,NewsTitle,AdminName, NewsImage,DATE_FORMAT(NewsTime,\"%Y-%m-%d %H:%i\") AS Time,substring(NewsContent,1,20) as count FROM news";
			return getList(sql);
	}
	
	public Map<String,Object> getRecordAndPageCount() throws SQLException {
		String sql="SELECT COUNT(NewsID) as recodall,round(COUNT(NewsID)/?) as pageCount from news;";
		return getForMap(sql, pageSize);
	}
	
	public int addNews(Map<String,Object> map) throws SQLException {
		String sql="INSERT into news VALUES(NewsID,?,?,now(),?,?)";
		Object [] obj=new Object[] {
				map.get("title"),map.get("content"),map.get("author"),map.get("imageurl")
		};
		
		return updateAll(sql, obj);
	}
	public int delById(String id) throws SQLException {
		String sql="delete from news where NewsID=?";
		return updateAll(sql,id);
		
	}
	public int delAllByIds(String[] ids) throws SQLException {
		String sql="delete from news where NewsID=?";
		int i=0;
		for(String id:ids) {
			i=updateAll(sql,id);
			if(i==0) {
				return i;
			}
		}
		return i;
		
	}
	
	public List<Map<String, Object>> querylistName(String newsTitle) throws SQLException{
		String sql="select NewsID,NewsTitle,AdminName, NewsImage,DATE_FORMAT(NewsTime,\"%Y-%m-%d %H:%i\") AS Time,substring(NewsContent,1,20) as count FROM news where NewsTitle like ? ";
		return getList(sql,"%"+newsTitle+"%");
	}
	
	
}
