/**
 * @see 该js文件用于处理login.jsp页面的登录和注册
 * @author wangbin
 * @time 2017年11月15日 下午2:41:20
 */

 /**
  * @see 处理用户(普通用户或者管理员)登录
  * @author wangbin
  * @time 2017年11月15日 下午2:44:20
  */
function signin()
{
	/**
	 * xmlhttp XMLHttpRequest对象,用于在后台与服务器交换数据
	 * username 登录页面用户输入的用户名
	 * password 登录页面用户输入的密码
	 * loginError 登录页面的错误提示
	 * result   用户是否正确登录，若正确登录则返回true,否则返回false
	 */
	var xmlhttp, username, password, loginError,rememberusername, result = false;
	loginError = document.getElementById("loginError");
	username = document.getElementById("username").value;
	password = document.getElementById("password").value;
	rememberusername = document.getElementById("rememberusername").checked;
	//新建XMLHttpRequest对象
	//对于不同浏览器新建XMLHttpRequest对象方式不同
	if (window.XMLHttpRequest) {
		// 对于 IE7+, Firefox, Chrome, Opera, Safari浏览器
		xmlhttp = new XMLHttpRequest();
	} else {
		// 对于 IE6,IE5浏览器
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//向处理登录的servlet发送请求，该请求包含用户名和密码
	xmlhttp.open("POST", "loginServlet", false);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded;charset=UTF-8");
	xmlhttp.send("username=" + username + "&password=" + password +"&rememberusername="+ rememberusername);
	//处理servlet返回的结果
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		//如果servlet返回"incorrect",说明用户名或密码错误
		//显示错误提示
		//登录失败
		if (xmlhttp.responseText == "incorrect") {
			loginError.style.display = "block";
			
			result = false;
		} else {
		    //如果servlet没有返回"incorrect",说明，用户名、密码正确
			//根据账号的不同类型，servlet会返回不同的地址
			//若账号类型为管理员，则servlet会返回管理员首页面的地址
			//若账号类型为普通用户，则servlet会返回普通用户首页面的地址
			//不显示错误提示
			//登录成功
			//跳转到相应首页面
			loginError.style.display = "none";
			document.forms['login_form'].action=xmlhttp.responseText;
			result = true;
		}
	}
	return result;
}

/**
  * @see 隐藏登录界面，显示注册界面
  * @author wangbin
  * @time 2017年11月15日 下午3:03:20
 */
function toRegister(){
	
	document.getElementById("login").style.display="none";
	document.getElementById("register").style.display="block";
	
}

/**
 * @see 隐藏注册界面，显示登录界面
 * @author wangbin
 * @time 2017年11月15日 下午3:05:20
*/
function toLogin(){
	$("register").hide();
	$("login").show();
}
/**
 * @see 处理普通用户注册
 * @author wangbin
 * @time 2017年11月15日 下午3:25:02
 */
function signup(){
	var userNameSignUp,passwordSignUp,passwordSignUp_confirm,signUpError,xmlhttp;
	userNameSignUp = document.getElementById("userNameSignUp").value;
	passwordSignUp = document.getElementById("passwordSignUp").value;
	passwordSignUp_confirm = document.getElementById("passwordSignUp_confirm").value;
	signUpError = document.getElementById("signUpError");
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	if(passwordSignUp != passwordSignUp_confirm){
		signUpError.style.display = "block";
		 signUpError.innerHTML="两次密码不一致";
			return false;
		}else{
	
		xmlhttp.open("POST","signUpServlet",false);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send("userNameSignUp="+userNameSignUp+"&passwordSignUp="+passwordSignUp);
		
				  if (xmlhttp.readyState==4 && xmlhttp.status==200)
				    {	
					  if(xmlhttp.responseText=="success"){			  
								 signUpError.style.display = "none";
								 alert("注册成功!");
								  return true;				 
					  }else if(xmlhttp.responseText=="error"){
						  signUpError.innerHTML="该用户名已经注册 !";
						  signUpError.style.display = "block";
						 
						return false;
					  }else{
						  signUpError.value="Register failly!";
						  signUpError.style.display = "block";
						 
							return false;
					  }
				    }

		} 
}
