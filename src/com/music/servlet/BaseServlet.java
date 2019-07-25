package com.music.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 实现的封装思路:java反射技术来完成Servlet的再一次封装
 */
public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String v=req.getParameter("v");//定义为一个做出请求处理的方法名
		Method method=null;
		try {
			 method=getClass().getMethod(v, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("此方法"+v+"不存在",e);
		}
		//找到方法要执行的方法，则马上调用
		try {
			String result=(String) method.invoke(this, req,resp);
			if(result !=null && !result.trim().isEmpty()) {
				int index=result.indexOf(":");
				String param=result.substring(0, index);
				String path=result.substring(index+1);//判断冒号后面的页面地址
				//System.out.println(param+" "+path);
				if(param.equals("f")) {//forward转发
					req.getRequestDispatcher(path).forward(req, resp);
				}else if(param.equals("r")) {//
					resp.sendRedirect(path);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("此方法"+v+"不能执行",e);
		}
		
		
		
	}
	
		
}
