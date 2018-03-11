/**
 * @see 用户处理banner.jsp
 * @author wangbin
 * @time 2017-11-18 15:37:20
 */
/**
 * @see 处理用户退出系统
 */
function exit(){
	layer.confirm('确认退出?', {icon: 3, title:'提示'}, function(index){
		  //若用户确定退出系统则删除名为username的cookie
		 delCookie();
		layer.close(index);
		});
}
/**
 * @see 删除名为username的Cookie
 */
function delCookie(){
	//通过ajax,发送一个请求给处理删除cookie的servlet
	//让servlet删除username这个cookie
		var url="deleteCookie";
		var xmlhttp, str;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				str = xmlhttp.responseText;
	          if(str == "success"){
	        	  //若成功删除，则调回到登录页面
	        	  window.location.href="login.jsp";
	          }
			}
		}
		xmlhttp.open("POST", url, false);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send();

} 