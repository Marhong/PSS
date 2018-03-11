<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="mytag" uri="MyTag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>公共头部</title>
<link href='../css/bootstrap.min.css' rel='stylesheet' type="text/css" />

</head>

<body>

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="customer_index.jsp" id="getSchedule"><span
				id="username"><mytag:ThirdTag /></span>的日程</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="query.jsp" id="querySchedule">查询日程</a></li>
				<li><a href="#" id="exit" onclick="exit()">退出</a></li>

			</ul>
		</div>
	</div>
	</nav>

	<script src="../js/banner.js"></script>
</body>
</html>