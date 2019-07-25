package com.music.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.music.dao.MovieDao;
import com.music.dao.NewsDao;

@WebServlet("/sys/User/movie.do")
public class MovieServlet extends BaseServlet {
	
	public String query(HttpServletRequest req,HttpServletResponse res) throws SQLException{
		MovieDao movieDao=new MovieDao();
		List<Map<String,Object>> list=movieDao.getMoviesInfo();
		req.setAttribute("list",list);
		return "f:/sys/User/movie.jsp";
		
	}
	public String upload(HttpServletRequest req,HttpServletResponse res) throws IOException, SQLException {
		DiskFileItemFactory disk=new DiskFileItemFactory();//初始化磁盘对象
		int max=1024*1024*5;//限制上传文件大小为5m
		int cache=1024*5*100;//缓存的大小为500kb
		disk.setSizeThreshold(cache);//设置加入文件缓存大小
		String savepath=getServletContext().getRealPath("/")+"mv/";//上传文件路径
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
					map.put("movieurl", "mv/"+fileName);
				}
				
			}
		}
		MovieDao movieDao=new MovieDao();
		movieDao.addMovies(map);
		return "r:movie.do?v=query";
	} 
	
	public String playMovie(HttpServletRequest req,HttpServletResponse res) {
		String url=req.getParameter("url");
		req.setAttribute("url",url);
		return "f:/sys/User/playmovie.jsp";
		
	}
	
}
