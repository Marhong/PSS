<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>弹出窗</title>
<link rel="stylesheet" type="text/css" href="../css/poupview.css" />
</head>
<body>


	<div class="layer-hidden-line" id="poupview">
		<form role="form" class="m-t-form" id="Form"
			action="customer_index.jsp"
			onsubmit="return addItem()" method="post">
			<input name="id" id="id" type="hidden" value="">

			<div class="row">
				<div class="col-xs-12 layer-condensed-case">
					<div class="form-group">
						<label for="classroomId"> 日程内容 </label> <span
							class="input-icon icon-left"> <input type="text"
							class="form-control" id="content" name="title"
							placeholder="请输入日程内容" data-required="true"
							data-descriptions="content" maxlength="50"> <i
							class="spl-icon-book-open"> </i>
						</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 layer-condensed-case">
					<div class="form-group">
						<label for="classroomId"> 开始时间 </label> <span
							class="input-icon icon-left"> <span id="sDate"></span> <input
							type="text" class="form-control date-picker-text" id="startTime"
							name="start" placeholder="请输入开始时间" data-required="true"
							data-descriptions="startTime" maxlength="50"> <i
							class="spl-icon-book-open"> </i>
						</span>
					</div>
				</div>
			</div>
			<div class="row" id="divEndTime">
				<div class="col-xs-12 layer-condensed-case">
					<div class="form-group">
						<label for="classroomId">结束时间</label> <span
							class="input-icon icon-left"> <span id="eDate"></span> <input
							type="text" class="date-picker-text form-control" id="endTime"
							name="end" placeholder="请输入结束时间" maxlength="50"> <i
							class="spl-icon-book-open"> </i>
						</span>
					</div>
				</div>
			</div>
			<div class="row" id="divEndTime">
				<div class="col-xs-12 layer-condensed-case">
					<div class="form-group">
						<label for="classroomId">提醒时间</label> <select class="form-control"
							id="remindTime">
							<option>30分钟前</option>
							<option>1小时前</option>
							<option>2小时前</option>
							<option>3小时前</option>
						</select>
					</div>
				</div>
			</div>
			<p id="addError"></p>
			<div class="row margin-top-10" id="options">


				<input type="button" value="删&nbsp;&nbsp;除"
					class="btn btn-danger btn-sm" id="del" onclick="deleteItem()" /> 
				<input
					type="button" value="确认修改" class="btn btn-warning btn-sm"
					id="modifyInf" onclick="mofifyItemInf()" /> 
				<input type="button"
					value="完成事宜" class="btn btn-success btn-sm" id="finish"
					onclick="finishItem()" /> 
				<input type="button" value="已完成"
					class="btn btn-warning btn-sm" id="modifyState"
					onclick="modifyItemState()" /> 
				<input type="submit" id="submit"
					value="提&nbsp;&nbsp;交" class="btn btn-primary btn-sm" />

			</div>

		</form>
	</div>
</body>
</html>