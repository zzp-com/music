package com.music.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.music.bean.AdminBean;

public class UserDao extends JdbcBuild {
	int pageSize=6;//设置每页想要显示多少条数据
	
	
	public List<Map<String,Object>> getUserInfo(int page) throws SQLException{
		String sql="select * from admin limit "+((page-1)*pageSize)+","+pageSize ;
		//System.out.println(sql);
		return getList(sql);
		
	}
	public int deleteUserInfoById(String userId) throws SQLException {
		String sql="delete from admin where AdminID=?";
		return updateAll(sql,userId);
	}
	public Map<String,Object> getRecordAndPageCount() throws SQLException {
		String sql="SELECT COUNT(AdminID) as recodall,round(COUNT(AdminID)/?) as pageCount from admin;";
		return getForMap(sql, pageSize);
	}
	
	public Map<String,Object> getUserInfoById(String id) throws SQLException{
		String sql="select * from admin where AdminID=?";
	
		return getForMap(sql, id);
	}
	
	public int updateById(String id,String name,String pwd,String time) throws SQLException {
			String sql="update admin set AdminName=?,AdminPwd=?,LastLoginTime=? where AdminId=?";
			
		return updateAll(sql,name,pwd,time,id);
		
	}
	public List<AdminBean> getAdminInfoByBean(int page) throws SQLException{
		String sql="select * from admin limit "+((page-1)*pageSize)+","+pageSize ;
		return getForBean(sql);
	}
	public List<Map<String,Object>> getUserInfoAjax(String username) throws SQLException{
		String sql="select * from admin where AdminName like ?";
		return getList(sql, "%"+username+"%");
	}
}
