$(function(){
   //初始化界面
   init();
   initFullCalendar();

   
})
function init(){
	document.getElementById('getSchedule').style.fontSize="30px";
	
}
 //页面加载完初始化日历   
var date = new Date();
var d = date.getDate();
var m = date.getMonth();
var y = date.getFullYear();

var addIndex;
//打开弹出层
function openLayer(date){
	//这个data是个Date对象
    var mydate = new Date();
    var hour = mydate.getHours();
    var minute = mydate.getMinutes();
    minute = minute>9?minute:"0"+minute;
    var eHour = hour+2;
    eHour = eHour<25?eHour:24;
    var second = mydate.getSeconds();
    second = second>9?second:"0"+second;
    var now = hour+":"+minute+":"+second;
    var e = eHour+":00:00";
    $('#id').val('');
    $("#content").val("");
    $("#del").hide();
    $("#modifyInf").hide();
    $("#finish").hide();
    $("#modifyState").hide();
    $("#submit").show();
    $('#addError').hide();
   
    var sTime = laydate.render({
    	  elem: '#startTime'
    	  ,type: 'time'
    	  ,value: now
    	});
  
    var eTime = laydate.render({
    	  elem: '#endTime'
    	  ,type: 'time'
    	  ,value:e
    	});
    $('#sDate').val(formatDate(date));
    $('#eDate').val(formatDate(date));
    addIndex=layer.open({
        title : '新增日程',
        type : 1,
        fix : false,
        skin : 'layui-layer-demo',
        // 加上边框
        area : [ '500px', '400px' ],
        // 宽高
        content : $('#Form')
    });
   
}
//打开编辑弹出窗
function openEditLayer(data){
	$("#id").val(data.id);
	showOptions(data.color,data.id);
    $("#del").show();
    $('#Form')[0].reset();
    $('#endTimeChk').prop("checked",false);
    $('#content').val(data.title);
    $('#startTime').val(getDateTime(data.start));
    $('#endTime').val(getDateTime(data.end));
    $("#addError").hide();
    $('#sDate').val(formatDate(data.start));
    $('#eDate').val(formatDate(data.end));
   
    setRemindTime(data.id);
    var sTime = laydate.render({
  	  elem: '#startTime'
  	  ,type: 'time'
  	});

  var eTime=laydate.render({
  	  elem: '#endTime'
  	  ,type: 'time'
  	});
    addIndex=layer.open({
        title : '编辑日程',
        type : 1,
        fix : false,
        skin : 'layui-layer-rim',
        // 加上边框
        area : [ '500px', '420px' ],
        // 宽高
        content : $('#Form')
    });
}
//关闭弹出层
function closeLayer(){
    layer.close(addIndex);
}
//初始化日程视图
function initFullCalendar(){
	
    $('#calendar').fullCalendar({

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay',
        },
         //这里这么写是为了汉化，但是只能汉化这一个页面，彻底汉化我也不懂
        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],  
        monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], 
        dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],  
        dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], 
        buttonText:{
            today:    '今天',
            month:    '月',
            week:     '周',
            day:      '日',
            },
            
           // 本来日历上的日程是可以拖动的，但是要处理那些又要多很多事，所以我设置不能拖动
        editable: false,//不能拖动   
        firstDay:1,
        timeFormat: 'H:mm',
        axisFormat: 'H:mm',
         events:"getSchedule",
        dayClick: function(date, allDay, jsEvent, view) { //当单击日历中的某一天时，触发callback

            openLayer(date);

        },
        eventClick:function(event, jsEvent, view){//当点击日历中的某一日程（事件）时，触发此操作
            openEditLayer(event);

        },
        eventMouseover:function (event, jsEvent, view){//鼠标划过的事件
            console.log(event);
        },eventMouseout:function( event, jsEvent, view ) { //鼠标离开的事件
            console.log(event);
        }
    });
}
//进入下一个月视图
function next(){
    $('#calendar').fullCalendar('next'); 
}
//添加一项日程
function addItem(){
	var url = "addScheduleItem";
	var content = document.getElementById("content").value;
	var addError = document.getElementById("addError");
	var remindTime = $('#remindTime').val();
    var start = $('#sDate').val()+" "+$('#startTime').val();
    var end = $('#eDate').val()+" "+$('#endTime').val();
    var id = $('#id').val();
    var timestamp = Date.parse(new Date(start));
    var timestamp2 = Date.parse(new Date(end));
    timestamp = timestamp / 1000;
    timestamp2 = timestamp2 / 1000;
    remindTime = getRemindTime(remindTime);
	if(content == null || content ==''){
		 addError.style.display = "block";
		 addError.innerHTML ="请填入日程内容";
		 return false;
	}else if(timestamp2<timestamp){
		addError.style.display = "block";
		addError.innerHTML = "结束时间必须晚于开始时间";
		return false;
	}
	if(submit(content,timestamp,timestamp2,remindTime,url,id)){
		closeLayer();
		layer.msg('成功添加事宜', {
			  icon: 6,
			  time: 10000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
			  return true;
			});   
		
	}else{
		layer.msg('添加事宜失败', {
			  icon: 5,
			  time: 5000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
			  return false;
			}); 
	}
}
//获取提醒时间
function getRemindTime(remindTime){
	if(remindTime == "30分钟前"){
		return 60*30;
	}else if(remindTime == "1小时前"){
		return 60*60;
	}else if(remindTime == "2小时前"){
		return 60*60*2;
	}else{
		return 60*60*3;
	}
}
//提交
function submit(content,timestamp,timestamp2,remindTime,url,id){
    
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	
		xmlhttp.open("POST",url,false);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send("content="+content+"&start="+timestamp+"&end="+timestamp2+"&remindTime="+remindTime+"&id="+id);
				  if (xmlhttp.readyState==4 && xmlhttp.status==200)
				    {	
					  if(xmlhttp.responseText=="success"){			  
								  return true;				 
					  	}
				    }
}

//将一个Date对象转为一个字符串
function formatDate(date){
	var mydate = new Date(date);
    var y = mydate.getFullYear();
    var month = mydate.getMonth()+1;
    var day = mydate.getDate();
  
    var hour = mydate.getHours();
    var minute = mydate.getMinutes();
    minute = minute>9?minute:"0"+minute;
    var second = mydate.getSeconds();
    second = second>9?second:"0"+second;
    var selecteddate = y+"-"+month+"-"+day;
    return selecteddate;
}
//将一个Date对象转为一个字符串
function getDateTime(date){
       var mydate = new Date(date);
	    var hour = mydate.getHours();
	    hour -=8;
	    hour = hour<0?hour+24:hour;
	    var minute = mydate.getMinutes();
	    minute = minute>9?minute:"0"+minute;

	    var second = mydate.getSeconds();
	    second = second>9?second:"0"+second;
	
	    var now = hour+":"+minute+":"+second;
    return now;
}
//通过AJAX从后端获取数据
function getJsonData(url) {

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
			"application/x-www-form-urlencoded;charset=UTF-8");
	xmlhttp.send();
	if (str != null) {
		var usersJson = eval('(' + str + ')');
		return usersJson;
	}
}
//获取一项日程的ID
function getItemById(id){
	var schedule = getJsonData("getSchedule");
	for(var i=0;i<schedule.length;i++){
		if(schedule[i].id == id){
			return schedule[i];
		}
	}
}
//设置提醒时间
function setRemindTime(id){
	var schedule = getJsonData("getSchedule");
	for(var i=0;i<schedule.length;i++){
		if(schedule[i].id == id){
			var remind;
			var remindTime = schedule[i].remindTime;
			if(remindTime == 1800){
				remind = "30分钟前";
			}else if(remindTime == 3600){
				remind = "1小时前";
			}else if(remindTime == 7200){
				remind = "2小时前";
			}else{
				remind = "3小时前";
			}
			$('#remindTime').val(remind);
		}
	}
}
//显示选项，不同状态的日程显示的选项也不同
function showOptions(color,id){

  var scheduleItem = getItemById(id);
	if(color == "yellow"){
		
		$("#del").show();
		$("#submit").hide();
		$("#modifyInf").show();
		$("#finish").hide();
		$("#modifyState").hide();
	}else if(color == "green"){
//		document.getElementById("reset").style.display = "none";
	
		$("#del").show();
		$("#submit").hide();
		$("#modifyInf").hide();
		$("#finish").hide();
		$("#modifyState").hide();
	}else if(color == "gray"){
		
		
		$("#del").show();
		$("#submit").hide();
		$("#modifyInf").hide();
		$("#finish").hide();
		$("#modifyState").show();
	}else{
		
		$("#del").show();
		$("#submit").hide();
		$("#modifyInf").show();
		$("#finish").show();
		$("#modifyState").hide();
	}
}
//删除一项日程
function deleteItem(){

	layer.confirm('确认删除该事宜?', {icon: 3, title:'提示'}, function(index){
		  //do something
		var url = "deleteItem";
	    var id = $('#id').val();
		if(submit(null,null,null,null,url,id)){
			closeLayer();
			window.location.href="customer_index.jsp";
			layer.msg('成功删除事宜', {
				  icon: 6,
				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					
				});   
			
		}else{
			closeLayer();
			layer.msg('删除事宜失败', {
				  icon: 5,
				  time: 3000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					initFullCalendar();
				}); 
		}
		  
		});

}
//修改一项日程信息
function mofifyItemInf(){
	var url = "updateItem";
	var content = document.getElementById("content").value;
	var addError = document.getElementById("addError");
	var remindTime = $('#remindTime').val();

    var start = $('#sDate').val()+" "+$('#startTime').val();

    var end = $('#eDate').val()+" "+$('#endTime').val();
    var id = $('#id').val();

    var timestamp = Date.parse(new Date(start));
    var timestamp2 = Date.parse(new Date(end));
    timestamp = timestamp / 1000;
    timestamp2 = timestamp2 / 1000;
    remindTime = getRemindTime(remindTime);
  
	if(content == null || content ==''){
		 addError.style.display = "block";
		 addError.innerHTML ="请填入日程内容";
		 return false;
	}else if(timestamp2<timestamp){
		addError.style.display = "block";
		addError.innerHTML = "结束时间必须晚于开始时间";
		return false;
	}
   
	if(submit(content,timestamp,timestamp2,remindTime,url,id)){
		closeLayer();
		window.location.href="customer_index.jsp";
		layer.msg('成功修改事宜', {
			  icon: 6,
			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
				
			});   
		
	}else{
		layer.msg('修改事宜失败', {
			  icon: 5,
			  time: 5000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
				initFullCalendar();
			}); 
	}
}
//完成一项日程
function finishItem(){
	var success = "完成事宜";
	var fail = "完成事宜失败";
	finishAndModify(success,fail);
}
//修改一项日程
function modifyItemState(){
	var success = "成功修改事宜";
	var fail = "修改事宜失败";
	finishAndModify(success,fail);
}
//完成或者修改一项日程
function finishAndModify(success,fail){
	layer.confirm('确认已经完成该事宜?', {icon: 3, title:'提示'}, function(index){
		var url = "changeItemState";
	    var id = $('#id').val();
		if(submit(null,null,null,null,url,id)){
			closeLayer();
			window.location.href="customer_index.jsp";
			layer.msg(success, {
				  icon: 6,
				  time: 5000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					
				});   
			
		}else{
			closeLayer();
			layer.close(index);
			layer.msg(fail, {
				  icon: 5,
				  time: 3000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					initFullCalendar();
				}); 
		}
		});
}