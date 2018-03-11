<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="mytag" uri="MyTag"%>
<!-- 这是一个自定义标签，用于判断用户是否登录，若用户没有登录则将转到登录界面 -->
<!-- 本来可以写一个过滤器就可以简单实现该功能而不需要像这样到处写 -->
<!-- 但是由于种种原因还是这么干了，虽然我也觉得有点傻 -->
<mytag:SecondTag />
<!DOCTYPE>
<html>
<head>
<title>我的日程</title>
<link href='../css/customer_index.css' rel='stylesheet' type="text/css" />

<!-- 这几个文件:fullcalendar.min.css、fullcalendar.print.min.css、moment.min.js、jquery.min.js、fullcalendar.min.js就是我们需要引入的文件了 -->
<link href='../css/fullcalendar.min.css' rel='stylesheet'
	type="text/css" />
<link href='../css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' type="text/css" />
<script src='../js/moment.min.js'></script>
<script src='../js/jquery.min.js'></script>
<script src='../js/fullcalendar.min.js'></script>

<!-- 注意这个文件:myfullcalendar.js，这是处理整个系统的业务逻辑的核心文件 -->
<script src="../js/myfullcalendar.js"></script>

<!-- 至于这些:layer-v3.1.0、layDate-v5.0.85、bootstrap.min.css，都是UI框架，可以自己去网上搜索了解更多 -->
<script src="../layer-v3.1.0/layer/layer.js"></script>
<script src="../layDate-v5.0.85/laydate/laydate.js"></script>
<link href='../css/bootstrap.min.css' rel='stylesheet' type="text/css" />
</head>
<body>
	<!-- 	引入公共头部 -->
	<div>
		<jsp:include page="banner.jsp" />

	</div>
	<!-- 	展示日历的div -->
	<div id="calendar"></div>
	
<!-- 	弹出窗 -->
	<jsp:include page="poupview.jsp"></jsp:include>
	
	<!-- 	引入公共底部 -->
	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
