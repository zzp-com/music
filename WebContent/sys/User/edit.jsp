<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑</title>
</head>
<body>
	<form action="user.do" method="post" >
	<input type="hidden" name="v" value="updateById">
	<input type="hidden" name="id" value="${map.AdminID}">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text"name="AdminName" value="${map.AdminName}"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="AdminPwd" value="${map.AdminPwd}"></td>
			</tr>
			<tr>
				<td>登录时间</td>
				<td><input type="text" name="LastLoginTime" value="${map.LastLoginTime}"></td>
			</tr>
			<tr>
				<td style="display:none;"><input type="hidden" name="v" value="login"></td>
				<td><input type="submit" value="确定修改"></td>
				<td><input type="button" value="返回列表" id="back"></td>
				
			</tr>
		</table>
	</form>
	<script>
		window.onload=function(){
			var back=document.getElementById("back");
			back.onclick=function(){
				window.location.href="user.do?v=query";
			}
		}
	</script>
</body>
</html>