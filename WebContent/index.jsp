<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<style>
	.slidePrev  {
    background: #ddd;
    width: 50px;
    height: 50px;
    position: absolute;
    top:40%;
    left: 0px;
    opacity: 0.5;
   text-align:center;
}
.slideNext {
    background: #ddd;
    width: 50px;
    height: 50px;
    position: absolute;
    top: 40%;
    right: 0px;
    opacity: 0.5;
    text-align:center;
}
#div {
	 text-align:center;
	 left:-50px;
	 display: table-cell;
	  vertical-align: middle;
}
</style>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<div style="width:100%;height:500px;">
		<div class="slidePrev" style="left: 0px; top: 252px;" onmouseover="showOp(this)" onmouseout="hiddenOp(this)">
			<span  >前</span>
		</div>
		<div style="text-align:center;" id="div"> 
					<img  src="images/图1.png" style="display:block;text-align:center;">
			
					<img  src="images/图2.png" style="display:none;">
				
					<img  src="images/图3.png" style="display:none;">

		</div>
		
		<div class="slideNext" style="right: 0px; top: 252px; opacity: 0.5;" onmouseover="showOp(this)" onmouseout="hiddenOp(this)" onclick="next();">
			<span >后</span>
		</div>
	</div>
	<input type="text" id="t01"><input type="button" id="t02" value="查询">
	<div id="div01"></div>
</body>
</html>