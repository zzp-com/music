package com.music.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class LoginServlet extends BaseServlet {
	public String login(HttpServletRequest req,HttpServletResponse res) {
		String name=req.getParameter("name");
		String password=req.getParameter("password");
			if("zz".equals(name)) {
				HttpSession session=req.getSession();
				session.setAttribute("name", name);
				
				return "r:/music/index.html";
			}else {
				return "r:/music/regedit.jsp";
			}
		
	}
}
