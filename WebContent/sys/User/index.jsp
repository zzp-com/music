<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示</title>
<style type="text/css">
	tr td{
		border:1px solid #0094ff;
	}
	table{
		margin:auto;
		text-align:center;
		}
		form{
			text-align:center;
		}
</style>
</head>
<body>
<div style="text-align:center;">
	<a href="/music/regedit.jsp">注册</a>
</div>
<div style="text-align:center;">
<input type="text" name="name" id="name">
	<a href="">查询</a>
	<input type="button" value="新增用户" id="add">
	
</div>
<form action="user.do" method="post">

<input type="submit" value="批量删除" id="del">
<input type="hidden" name="v" value="delAll">
	<table style="border:1px solid #0094ff;">
		<thead>
			<tr>
				<td><input type="checkbox" ></td>
				<td>编号</td>
				<td>名字</td>
				<td>密码</td>
				<td>最后登录时间</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="map">
				<tr>
					<td><input type="checkbox" name="ck" value="${map.AdminID}"></td>
					<td>${map.AdminID}</td>
					<td>${map.AdminName}</td>
					<td>${map.AdminPwd}</td>
					<td>${map.LastLoginTime}</td>
					<td>
					<a href="user.do?v=findById&id=${map.AdminID}">编辑</a>
					<a href="user.do?v=del&id=${map.AdminID}">删除</a>
					</td>
				</tr>
				
			</c:forEach>
			
			
		</tbody>
	</table>
	</form>
	<div style="text-align:center;">
		${map.recodall}条记录数 ${page}/${map.pageCount}页<a href="user.do?v=query&page=${page-1} ">上一页</a><span></span><a href="user.do?v=query&page=${page+1} ">下一页</a><input type="text" name="selpage" style="width:20px;">
		<button >go</button>
	</div>
</body>
</html>