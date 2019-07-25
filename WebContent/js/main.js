function showOp(obj){
	obj.style.cssText="background:red";//设置元素文本内容样式
	
}
function hiddenOp(obj){
	obj.style.cssText="";
	
}
function next(){
	var div=document.getElementById("div");
	var list=[];
	if(div.hasChildNodes()){
		var list2=div.childNodes;//获取标签下面的所有子元素
		var j=0
		for(i = 1;i<list2.length;i++){
			if(list2[i].nodeName == "img"){
				list[j]=list2[i];
				j++;
			}
		}
		for(i=0;i<list.length;i++){
			if(list[i].style.display == "block"){
				list[i].style.display=="none";
				if(i==list.length-1){
					list[i].style.display="block";
					return;
				}
				list[i+1].style.display=="block";
				return;
			}
		}
		
	}
}

$(function(){
	$("#t02").click(function(){
		var username=$("#01").val();//获取文本
		$("#div01").load();
	})
});
