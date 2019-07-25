<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片上传</title>
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
</head>
<body>
		<form action="news.do?v=upload" method="post" enctype="multipart/form-data">
		<table >
			<tr >
				<td>资讯名称</td>
				<td><input type="text"name="title" value=""></td>
			</tr>
			<tr>
				<td>资讯内容</td>
				<td><input type="text" name="content" value=""></td>
			</tr>
			<tr>
				<td>资讯作者</td>
				<td><input type="text" name="author" value=""></td>
			</tr>
			<tr>
				<td>资讯图片</td>
				<td><input type="file" name="image" value=""></td>
			</tr>
			
			<tr>
				<td></td>
				<td style="display:none;"><input type="hidden" name="v" value="login"></td>
				<td ><input type="submit" value="提交"></td>
				<td ><input type="button"  value="返回"></td>
			</tr>
		</table>
	</form>
</body>
</html>