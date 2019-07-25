<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试el</title>
</head>
<body>
	<c:forEach items="${listBean}" var="item">
		
		${item.adminName}<br>
		
	</c:forEach>
</body>
</html>