package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.music.servlet.BaseServlet;
import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;
@WebServlet("/response.do")
public class TestResponse extends BaseServlet {
	
	public String showpdf(HttpServletRequest req,HttpServletResponse res) throws IOException {
		//只有用户登录会才能看pdf
		//使用HttpSession：服务器的会话对象，可以跟踪用户的会话状态
		
		HttpSession session=req.getSession();
		String id=(String) session.getAttribute("name");
		if(id==null || "".equals(id)) {
			res.sendRedirect("regedit.jsp");
			return null;
		}
		
		
		/****************************/
		res.setContentType("application/pdf");
			String path=getServletContext().getRealPath("/")+"pdf/2010.pdf";
			File f=new File(path);
			InputStream is=null;
			OutputStream os=null;
			is=new FileInputStream(f);
			os=res.getOutputStream();
			int i=-1;
			byte[] b=new byte[1024];
			while((i=is.read(b)) !=-1) {
				os.write(b,0,i);
				
			}
			
			is.close();
			os.flush();
			os.close();
			
		return null;
		
	}
}
