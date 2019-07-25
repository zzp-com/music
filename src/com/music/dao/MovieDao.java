package com.music.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MovieDao extends JdbcBuild {
	int pageSize=6;//设置每页想要显示多少条数据
	public List<Map<String,Object>> getMoviesInfo() throws SQLException{
		String sql="select MovieID,MovieTitle,MovieUploader, MovieContent,DATE_FORMAT(MovieTime,\"%Y-%m-%d %H:%i\") AS Time FROM movies";
			return getList(sql);
	}
	public int addMovies(Map<String,Object> map) throws SQLException {
		String sql="INSERT into movies VALUES(MovieID,?,?,?,now())";
		Object [] obj=new Object[] {
				map.get("title"),map.get("author"),map.get("movieurl")
		};
		
		return updateAll(sql, obj);
	}
	
}
