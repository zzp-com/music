<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<style type="text/css">
</style>
</head>
<body>
	<form action="login.do?v=login">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text"name="name" value=""></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="pwd" value=""></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td><input type="text" name="pwdtwo" value=""></td>
			</tr>
			<tr>
				<td style="display:none;"><input type="hidden" name="v" value="login"></td>
				<td><input type="submit" value="登录"></td>
				<td><input type="button" value="注册"></td>
				<td><input type="reset"  value="重置"></td>
			</tr>
		</table>
	</form>

</body>
</html>