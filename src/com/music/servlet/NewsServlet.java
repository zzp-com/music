package com.music.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.music.dao.NewsDao;
import com.music.dao.UserDao;
import com.music.util.Tools;

import net.sf.json.JSONArray;

@WebServlet("/sys/User/news.do")
public class NewsServlet extends BaseServlet {
		
	public String query(HttpServletRequest req,HttpServletResponse res) throws SQLException, UnsupportedEncodingException {
		
		String page=req.getParameter("page");
		if(page == null || " ".equals(page)) {
			page="1";
		}
		page=Tools.navigation(page);
		NewsDao newdao=new NewsDao();
		Map<String,Object> map=null;
		List<Map<String,Object>> list=null;
		try {
			map=newdao.getRecordAndPageCount();
			String pageCount=map.get("pageCount").toString();
			if(Integer.parseInt(pageCount)<Integer.parseInt(page)||"0".equals(pageCount)) {
				page=map.get("pageCount").toString();
				map.put("pageCount", "1");
			}
			 list=newdao.getNewsInfo();
			
			// System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("list",list);
			req.setAttribute("map", map);
			return "f:/sys/User/manager.jsp";	
	}
	/**
	 * 添加信息包括文件上传的操作
	 * 1.文件上传是一种以二进制流方式完成
	 * 2，必须是表单方式提交，而且请求时post请求，需加入enctype属性
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public String upload(HttpServletRequest req,HttpServletResponse res) throws IOException, SQLException {
		DiskFileItemFactory disk=new DiskFileItemFactory();//初始化磁盘对象
		int max=1024*1024*5;//限制上传文件大小为5m
		int cache=1024*5*100;//缓存的大小为500kb
		disk.setSizeThreshold(cache);//设置加入文件缓存大小
		String savepath=getServletContext().getRealPath("/")+"images/";//上传文件路径
		File f=new File(savepath);
		if(!f.exists()) {//判断文件不存在
			f.mkdirs();
		}
		disk.setRepository(f);
		ServletFileUpload sfu=new ServletFileUpload(disk);
		sfu.setSizeMax(max);
		List<FileItem> list=null;
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			list=sfu.parseRequest(req);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(FileItem item:list) {
			if(item.isFormField()) {//判断现在获取的参数是普通form参数，除file以外
				String fileName=item.getFieldName();//获取参数名
				String value=item.getString("utf-8");//获取参数值
				map.put(fileName, value);
			}else {
				String fileName=item.getName();//上传文件名字
				if(!"".equals(fileName)) {
					//上传文件需要判断：上传文件就在缓冲里面，就可以直接将缓冲上传
					//如果文件大小大于缓存，我们就需要使用io流来处理
					FileOutputStream fos=new FileOutputStream(savepath+""+fileName);
					InputStream is=null;
					if(item.isInMemory()) {//判断是否在缓存之中
						fos.write(item.get());//直接将缓存中的二进制写入到服务器上
					}else {
						is=item.getInputStream();
						int i=-1;
						byte[] b=new byte[1024*5];
						while((i= is.read(b))!=-1) {
							fos.write(b, 0, i);
						}
						is.close();
					}
					fos.flush();
					fos.close();
					map.put("imageurl", "images/"+fileName);
				}
				
			}
		}
		NewsDao newsdao=new NewsDao();
		newsdao.addNews(map);
		return "r:news.do?v=query";
	}
	
	public String del(HttpServletRequest req,HttpServletResponse res) throws SQLException {
		String id=req.getParameter("id");
		NewsDao newsDao=new NewsDao();
		newsDao.delById(id);
		
		return "r:news.do?v=query";
		
	}
	public String delAll(HttpServletRequest req,HttpServletResponse res) throws SQLException {
		String[] ids=req.getParameterValues("ck");
		NewsDao newsDao=new NewsDao();
		newsDao. delAllByIds(ids);
		return "r:news.do?v=query";
		
	}
	
	public String queryAjax(HttpServletRequest req,HttpServletResponse res) throws SQLException, IOException {
		NewsDao newsDao=new NewsDao();
		
		String title=req.getParameter("title");
		res.setContentType("text/xml;charset=utf-8");
		List <Map<String,Object>> list=newsDao.querylistName(title.trim());
		PrintWriter out=res.getWriter();
		out.println("<zzp>");
		for(Map<String,Object> map:list) {
			out.println("<zzp1>");
			out.println("<NewsID>"+map.get("NewsID").toString()+"</NewsID>");
			out.println("<NewsTitle>"+map.get("NewsTitle").toString()+"</NewsTitle>");
			out.println("<NewsContent>"+map.get("count").toString()+"</NewsContent>");
			out.println("<NewsTime>"+map.get("Time").toString()+"</NewsTime>");
			out.println("<NewsImage>"+map.get("NewsImage").toString()+"</NewsImage>");
			out.println("<AdminName>"+map.get("AdminName").toString()+"</AdminName>");
			out.println("</zzp1>");
		}
		out.println("</zzp>");
		out.flush();
		out.close();
		return null;
	}
	
	public String queryJson(HttpServletRequest req,HttpServletResponse res) throws SQLException, IOException {
		NewsDao newsDao=new NewsDao();
		
		String title=req.getParameter("title");
		res.setContentType("text/xml;charset=utf-8");
		List <Map<String,Object>> list=newsDao.querylistName(title.trim());
//		for(Map<String,Object> map:list) {
//			map.get("")
//		}
		PrintWriter out=res.getWriter();
		out.println(JSONArray.fromObject(list));
		out.flush();
		out.close();
		return null;
	}
	
}
