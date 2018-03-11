/**
 * @see 处理query.jsp
 */

function init(){
	document.getElementById('querySchedule').style.fontSize="30px";

	
}

layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  //执行一个laydate实例
  laydate.render({
    elem: '#month' //指定元素
    ,type: 'month'
  });
});
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#day' //指定元素
	    
	  });
	});
function searchByState(){
	clearTbody("stateResult");
	document.getElementById("state").style.display="block";
	document.getElementById("time1").style.display="none";
	document.getElementById("time2").style.display="none";

}
function searchByMonth(){
	
	clearTbody("monthResult");
	document.getElementById("state").style.display="none";
	document.getElementById("time1").style.display="block";
	document.getElementById("time2").style.display="none";

}
function searchByDay(){
	
	clearTbody("dayResult");
	document.getElementById("state").style.display="none";
	document.getElementById("time1").style.display="none";
	document.getElementById("time2").style.display="block";

}
function getStateData(){
	clearTbody("stateResult");
	var url = "getScheduleByState";
	var inf = document.getElementById("stateChoice").value;
	
	var usersJson = getData(url,inf);
	
	if (usersJson.length != -1) {
		for (var i = 0; i < usersJson.length; i++) {

			var z = $("stateResult").rows.length;
			var tableRow = $("stateResult").insertRow(z);

			var Cell_0 = tableRow.insertCell(0);
			Cell_0.innerHTML = z;

			var Cell_1 = tableRow.insertCell(1);
			Cell_1.innerHTML = usersJson[i].title;

			var Cell_2 = tableRow.insertCell(2);
			Cell_2.innerHTML = usersJson[i].start;

			var Cell_3 = tableRow.insertCell(3);
			Cell_3.innerHTML = usersJson[i].end;

			var Cell_4 = tableRow.insertCell(4);
			Cell_4.innerHTML = usersJson[i].remindTime+"毫秒";

		}
	}
}
function getMonthData(){
	clearTbody("monthResult");
	var url = "getScheduleByMonth";
	var inf = document.getElementById("month").value;
	
	var usersJson = getData(url,inf);
	
	if (usersJson.length != -1) {
		for (var i = 0; i < usersJson.length; i++) {

			var z = $("monthResult").rows.length;
			var tableRow = $("monthResult").insertRow(z);

			var Cell_0 = tableRow.insertCell(0);
			Cell_0.innerHTML = z;

			var Cell_1 = tableRow.insertCell(1);
			Cell_1.innerHTML = usersJson[i].title;

			var Cell_2 = tableRow.insertCell(2);
			Cell_2.innerHTML = usersJson[i].start;

			var Cell_3 = tableRow.insertCell(3);
			Cell_3.innerHTML = usersJson[i].end;

			var Cell_4 = tableRow.insertCell(4);
			Cell_4.innerHTML = usersJson[i].remindTime+"毫秒";
			
			var Cell_5 = tableRow.insertCell(5);
			if(usersJson[i].color == "yellow"){
				Cell_5.innerHTML = "未提醒";
			}else if(usersJson[i].color == "red"){
				Cell_5.innerHTML = "提醒";
			}else if(usersJson[i].color == "green"){
				Cell_5.innerHTML = "已完成";
			}else{
				Cell_5.innerHTML = "未完成";
			}
			

		}
	}
}
function getDayData(){
	clearTbody("dayResult");
	var url = "getScheduleByDay";
	var inf = document.getElementById("day").value;
	
	var usersJson = getData(url,inf);
	
	if (usersJson.length != -1) {
		for (var i = 0; i < usersJson.length; i++) {

			var z = $("dayResult").rows.length;
			var tableRow = $("dayResult").insertRow(z);

			var Cell_0 = tableRow.insertCell(0);
			Cell_0.innerHTML = z;

			var Cell_1 = tableRow.insertCell(1);
			Cell_1.innerHTML = usersJson[i].title;

			var Cell_2 = tableRow.insertCell(2);
			Cell_2.innerHTML = usersJson[i].start;

			var Cell_3 = tableRow.insertCell(3);
			Cell_3.innerHTML = usersJson[i].end;

			var Cell_4 = tableRow.insertCell(4);
			Cell_4.innerHTML = usersJson[i].remindTime+"毫秒";
			var Cell_5 = tableRow.insertCell(5);
			if(usersJson[i].color == "yellow"){
				Cell_5.innerHTML = "未提醒";
			}else if(usersJson[i].color == "red"){
				Cell_5.innerHTML = "提醒";
			}else if(usersJson[i].color == "green"){
				Cell_5.innerHTML = "已完成";
			}else{
				Cell_5.innerHTML = "未完成";
			}
		}
	}
}
function $(id) {
	return document.getElementById(id);
}

function clearTbody(tableName) {

	var table = document.getElementById(tableName);
	var tableLength = table.rows.length;
	if (tableLength >= 1) {
		for (var int = 1; int < tableLength; int++) {
			table.deleteRow(1);

		}
	}

}
function getData(url,inf){
	
	var xmlhttp, str;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			str = xmlhttp.responseText;
          
		}
	}
	xmlhttp.open("POST", url, false);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send("inf="+inf);
	if (str != null) {
		var usersJson = eval('(' + str + ')');
		return usersJson;
	}
}