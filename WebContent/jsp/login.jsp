<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 	一个自定义的SimpleTag，用于记住用户名 -->
<%@taglib prefix="mytag" uri="MyTag"%>

<!DOCTYPE html>
<html>
<head>
<title>个人日程安排系统登录界面</title>

<!--    因为这个登录界面模板我也是从网上找的，除了login.css这个文件是我自己写的外，其他都是模板附带的，所以除了login.css最好别改动。 -->
<link rel="shortcut icon" href="../../favicon.ico">
<link rel="stylesheet" type="text/css" href="../css/demo.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="../css/login.css" />
</head>
<body>
	<div class="container">

		<header>
			<h1>个人日程安排系统</h1>
		</header>

		<section>
			<div id="container_demo">
				<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
					id="tologin"></a>
				<div id="wrapper">
				
				
					<!-- 				登录部分页面代码 -->
					<div id="login" class="animate form">
					
<!-- 					这个onsubmit属性是html5的form的一个属性，如果值为true则正常跳转到action的路径，如果值为false，则不能跳转 -->
<!-- 					  这里的onsubmit属性值用一个函数返回值是因为这个signin()函数要用于判断用户名密码是否正确有效 -->
						<form name="login_form" action="index.jsp" method="post"
							onsubmit="return signin()">
							<h1>登&nbsp&nbsp录</h1>
							<p>
							
<!-- 							这里的oninvalid属性和oninput属性也是input自带的属性 -->
<!-- 							oninvalid="setCustomValidity('请输入用户名')就是当用户输入不合法时提示"请输入用户名" -->
<!-- 							oninput="setCustomValidity('')就是当用户输入时调用这个函数-->
<!-- 							注意这两个属性要成对出现才能实现提示效果 -->
								<label for="username" class="uname"> 用户名 </label> <input
									id="username" name="username" required
									oninvalid="setCustomValidity('请输入用户名')"
									oninput="setCustomValidity('')" placeholder="my user name"
									value="<mytag:FourthTag/>" />
							</p>
							<p>
								<label for="password" class="password"> 密码 </label> <input
									id="password" name="password" required
									oninvalid="setCustomValidity('请输入密码')"
									oninput="setCustomValidity('')" type="password"
									placeholder="my password" />
							</p>
							<p id="loginError">用户名或密码错误</p>
							<p class="keeplogin">
								<input type="checkbox" name="rememberusername"
									id="rememberusername" value="loginkeeping" /> <label
									for="loginkeeping">记住账号</label>
							</p>
							<p class="login button">
								<input type="submit" value="Login" />

							</p>
							<p class="change_link">
								没有账号 ? <a href="#toregister" class="to_register">注&nbsp册</a>
							</p>
						</form>
					</div>
					
					
					<!-- 					注册部分页面代码 -->
					<div id="register" class="animate form">
						<form action="login.jsp#tologin"
							onsubmit="return signup()" method="post">
							<h1>注&nbsp&nbsp册</h1>
							<p>
								<label for="userNameSignUp" class="uname"> 用户名 </label> <input
									id="userNameSignUp" name="userNameSignUp" required
									oninvalid="setCustomValidity('请输入要想注册的用户名')"
									oninput="setCustomValidity('')" placeholder="my username" />
							</p>
							<p>
								<label for="passwordSignUp" class="youpasswd"> 密码 </label> <input
									id=passwordSignUp name=passwordSignUp required
									oninvalid="setCustomValidity('请输入你的密码')"
									oninput="setCustomValidity('')" type="password"
									placeholder="my password" />
							</p>
							<p>
								<label for="passwordSignUp_confirm" class="youpasswd">
									确认密码 </label> <input id="passwordSignUp_confirm"
									name="passwordsignup_confirm" required
									oninvalid="setCustomValidity('请输入你的密码')"
									oninput="setCustomValidity('')" type="password"
									placeholder="my password" />
							</p>
							<p id="signUpError" class="keeplogin"></p>
							<p class="signin button">
								<input type="submit" value="Sign up" />
							</p>
							<p class="change_link">
								已注册账号 ? <a href="#tologin" class="to_register"> 登&nbsp录 </a>
							</p>
						</form>
					</div>

				</div>
			</div>
		</section>
	</div>
	
<!-- 引入处理用户登录&注册的js文件 -->
	<script src="../js/signin.js" charset="UTF-8"></script>

</body>
</html>

<!-- 	引入公共尾部 -->
<jsp:include page="footer.jsp" />
