<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频管理</title>
<style type="text/css">
tr td {
	border: 1px solid #0094ff;
}

table {
	margin: auto;
	text-align: center;
}

form {
	text-align: center;
}
</style>

<script>
window.onload=function(){
	var add=document.getElementById("add");
	add.onclick=function(){
		window.location.href="addmovie.jsp";
	}
}
</script>
</head>
<body>

	<div style="text-align: center;">
		<input type="text" name="name" id="name"> <a href="">查询</a> <input
			type="button" value="上传视频" id="add">
	</div>
	<form action="news.do?v=delAll" method="post">

		<input type="submit" value="批量删除" id="del"> <input
			type="hidden" name="v" value="delAll">
		<table style="border: 1px solid #0094ff;">
			<thead>
				<tr>
					<td><input type="checkbox"></td>
					<td>编号</td>
					<td>主题</td>
					<td>内容</td>
					<td>时间</td>
					<td>上传者</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="map">
					<tr>
						<td><input type="checkbox" name="ck" value="${map.MovieID}"></td>
						<td>${map.MovieID}</td>
						<td>${map.MovieTitle}</td>
						<td>${map.Time}</td>
						<td><img src="/music/${map.MovieContent}"></td>
						<td>${map.MovieUploader}</td>
						<td><a href="#?v=findById&id=${map.MovieID}">编辑</a> 
							<a href="movie.do?v=playMovie&url=${map.MovieContent}">预览</a></td>
					</tr>

				</c:forEach>


			</tbody>
		</table>
	</form>
	<div style="text-align: center;">
		${map.recodall}条记录数 ${page}/${map.pageCount}页<a
			href="news.do?v=query&page=${page-1} ">上一页</a><span></span><a
			href="news.do?v=query&page=${page+1} ">下一页</a><input type="text"
			name="selpage" style="width: 20px;">
		<button>go</button>
	</div>
</body>
</html>