package com.music.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.music.bean.AdminBean;
import com.music.dao.UserDao;
import com.music.util.Tools;

@WebServlet("/sys/user.do")
public class UserServlet extends BaseServlet {

	public String query(HttpServletRequest req,HttpServletResponse res) {
		List<Map<String, Object>> list = null;
		List<AdminBean> listBean=null;
		String page=req.getParameter("page");
		if(page == null || " ".equals(page)) {
			page="1";
		}
		page=Tools.navigation(page);
		UserDao userdao = new UserDao();
		Map<String,Object> map=null;
		try {
			map=userdao.getRecordAndPageCount();
			if(Integer.parseInt( map.get("pageCount").toString())<Integer.parseInt(page)) {
				page=map.get("pageCount").toString();
			}
			list = userdao.getUserInfo(Integer.parseInt(page));
			listBean=userdao.getAdminInfoByBean(Integer.parseInt(page));
			// System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("listBean", listBean);
		req.setAttribute("list", list);
		req.setAttribute("page", page);//保存当前页码，为jsp页面中下一页做参数
		req.setAttribute("map", map);
		//return "f:/userjsp.jsp";
		return "f:/sys/User/index.jsp";
	}

	public String del(HttpServletRequest req,HttpServletResponse res) {
		String id = req.getParameter("id");
		
		UserDao userdao = new UserDao();
		try {
			int i=userdao.deleteUserInfoById(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "r:user.do?v=query";
	}
	
	//查询
	public String findById(HttpServletRequest req,HttpServletResponse res) throws SQLException {
		String id=req.getParameter("id");
		UserDao userDap=new UserDao();
		Map<String,Object> map=userDap.getUserInfoById(id);
			req.setAttribute("map", map);
		return "f:/sys/User/edit.jsp";
		
	}
	
	
	public String delAll(HttpServletRequest req,HttpServletResponse res) {
		String[] del=req.getParameterValues("ck");
		for(String s:del) {
			System.out.println(s);
		}
		return "r:user.do?v=query";
	}
	public String updateById(HttpServletRequest req,HttpServletResponse res) throws SQLException {
		String id=req.getParameter("id");
		String AdminName=req.getParameter("AdminName");
		String AdminPwd=req.getParameter("AdminPwd");
		String LastLoginTime=req.getParameter("LastLoginTime");
		UserDao userDao=new UserDao();
		userDao.updateById(id, AdminName, AdminPwd, LastLoginTime);
		return "r:user.do?v=query";
	}
	/**
	 * 一个ajax请求方法
	 * @param req
	 * @param res
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public String queryByUserNameAjax(HttpServletRequest req,HttpServletResponse res) throws SQLException, IOException {
		String username=req.getParameter("username");
		UserDao userDao=new UserDao();
		List<Map<String,Object>> list=userDao.getUserInfoAjax(username);
		res.setCharacterEncoding("utf-8");
		PrintWriter out=res.getWriter();
		for(Map<String,Object> map:list) {
			out.println(map.get("AdminID")+" "+map.get("AdminName"));
		}
		out.flush();
		out.close();
		return null;
		
	}

}
