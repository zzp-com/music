package com.music.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebFault;
@WebFilter("/sys/*")
public class AccessFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) arg0;
		req.setCharacterEncoding("utf-8");
		HttpServletResponse res=(HttpServletResponse) arg1;
		HttpSession session=req.getSession();
		String name=(String) session.getAttribute("name");
		String v=req.getParameter("v");
		
		
		if(name == null || "".equals(name)) {
			StringBuffer sb=req.getRequestURL();
			sb.append("?v="+v);
			
			res.sendRedirect("/music/regedit.jsp?redirecturl="+sb.toString());
			
		}else {
			arg2.doFilter(arg0, arg1);//调用下一个过滤链
		}
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
