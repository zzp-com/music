<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资讯管理</title>
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
		window.location.href="add.jsp";
	}
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#find").click(function(){//jQuery中表单提交的事件
			var title=$("#name").val();
			$.post("news.do",{"v":"queryAjax","title":title},function(data){
				$("#tbody").empty();
				var content="";
				$(data).find("zzp1").each(function(i){
					var NewsID= $(this).children("NewsID").text();
					var NewsTitle= $(this).children("NewsTitle").text();
					var NewsContent= $(this).children("NewsContent").text();
					var NewsTime= $(this).children("NewsTime").text();
					var NewsImage= $(this).children("NewsImage").text();
					var AdminName= $(this).children("AdminName").text();
					content+="<tr>";
					content+="<td>"+NewsID+"</td>";
					content+="<td>"+NewsTitle+"</td>";
					content+="<td>"+NewsContent+"</td>";
					content+="<td>"+NewsTime+"</td>";
					content+="<td>"+"<img src=' /music/"+NewsImage+"'></td>";
					content+="<td>"+AdminName+"</td>";
					content+="</tr>";
				});
				$("#tbody").append(content);
			},"xml")
			return false;
		});
		
		$("#find2").click(function(){
			var title=$("#name").val();
			$.post("news.do",{"v":"queryJson","title":title},function(data){
				$("#tbody").empty();
				var content="";
				$.each(data,function(i,value){
					content+="<tr>";
					
					content+="<td>"+value.NewsID+"</td>";
					content+="<td>"+value.NewsTitle+"</td>";
					content+="<td>"+value.count+"</td>";
					content+="<td>"+value.Time+"</td>";
					content+="<td>"+"<img src=' /music/"+value.NewsImage+"'></td>";
					content+="<td>"+value.AdminName+"</td>";
					content+="</tr>";
				});
				$("#tbody").append(content);
			},"json");
			return false;
		});
		
	});
	
	
	
</script>
</head>
<body>

	<div style="text-align: center;">
		
	</div>
	<form action="news.do" method="post">
	<input type="text" name="name" id="name"> <button id="find2">查询</button>
		<input type="submit" value="批量删除" id="del"> <input
			type="hidden" name="v" value="delAll">
			 <input type="button" value="新增资讯" id="add">
		<table style="border: 1px solid #0094ff;">
			<thead>
				<tr>
					<td><input type="checkbox"></td>
					<td>编号</td>
					<td>主题</td>
					<td>内容</td>
					<td>时间</td>
					<td>图片</td>
					<td>作者</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${list}" var="map">
					<tr>
						<td><input type="checkbox" name="ck" value="${map.NewsID}"></td>
						<td>${map.NewsID}</td>
						<td>${map.NewsTitle}</td>
						<td>${map.count}</td>
						<td>${map.Time}</td>
						<td><img src="/music/${map.NewsImage}"></td>
						<td>${map.AdminName}</td>
						<td><a href="user.do?v=findById&id=${map.NewsID}">编辑</a> <a
							href="news.do?v=del&id=${map.NewsID}">删除</a></td>
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