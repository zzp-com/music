<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>播放</title>
 <style>
   body { font: 12px "Myriad Pro", "Lucida Grande", sans-serif; text-align: center; padding-top: 5%; }
   .flowplayer { width: 80%; }
   </style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/skin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/flowplayer.min.js"></script>
</head>
<body>
	<div class="flowplayer" data-ratio="0.565" data-swf="${pageContext.request.contextPath}/js/flowplayer.swf">
		<video >
			<source src="${pageContext.request.contextPath}/${url}" type="video/mp4">
		</video>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#test").click(function(){
			alert("qwe");
		});
	});
</script>
</html>