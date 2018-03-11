<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="mytag" uri="MyTag"%>
<mytag:SecondTag />
<!DOCTYPE>
<html>
<head>
<title>查询日程</title>
<link rel="stylesheet" href="../css/query.css">
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../layui/layui.all.js"></script>
<script src="../layui/layui.js"></script>
</head>
<body onload="init()">

	<div id="header">
		<jsp:include page="banner.jsp" />
	</div>

	<div id="content">

		<div class="layui-tab">
			<ul class="layui-tab-title">
				<li class="layui-this" onclick="searchByState()">按状态查询</li>
				<li onclick="searchByMonth()">按月查询</li>
				<li onclick="searchByDay()">按天查询</li>
			</ul>
			<div class="layui-tab-content">
<!-- 			按状态搜索的页面内容 -->
				<div class="layui-tab-item layui-show" id="state">

					<div id="stateForm">
						<form class="form-inline">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">状态</div>
									<select class="form-control" name="choice" id="stateChoice">
										<option>尚未进入提醒时间的带完成事宜</option>
										<option>进入提醒时间的带完成事宜</option>
										<option>已完成的事宜</option>
										<option>未完成的事宜</option>

									</select>

								</div>
							</div>
							<button type="button" class="btn btn-primary"
								onclick="getStateData()">查询</button>

						</form>
					</div>


					<table id="stateResult" class="table table-striped">
						<tr>
							<td>序号</td>
							<td>内容</td>
							<td>开始时间</td>
							<td>结束时间</td>
							<td>提醒时间</td>
						</tr>


					</table>


				</div>

<!-- 				按月份搜索的页面内容 -->
				<div class="layui-tab-item" id="time1">
					<div id="stateForm">
						<form class="form-inline">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">月份</div>
									<input type="text" class="form-control" id="month" name="month">
								</div>
							</div>
							<button type="button" class="btn btn-primary"
								onclick="getMonthData()">查询</button>
						</form>

					</div>

					<table class="table table-striped" id="monthResult">
						<tr>
							<td>序号</td>
							<td>内容</td>
							<td>开始时间</td>
							<td>结束时间</td>
							<td>提醒时间</td>
							<td>状态</td>
						</tr>

					</table>

				</div>
<!-- 				按日期搜索的页面内容 -->
				<div class="layui-tab-item" id="time2">
					<div id="stateForm">
						<form class="form-inline">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">天</div>
									<input type="text" class="form-control" id="day" name="day">
								</div>
							</div>
							<button type="button" class="btn btn-primary"
								onclick="getDayData()">查询</button>
						</form>

					</div>

					<table class="table table-striped" id="dayResult">
						<tr>
							<td>序号</td>
							<td>内容</td>
							<td>开始时间</td>
							<td>结束时间</td>
							<td>提醒时间</td>
							<td>状态</td>
						</tr>

					</table>

				</div>
			</div>
		</div>
	</div>


	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div>
	<script src="../js/query.js"></script>
</body>
</html>
