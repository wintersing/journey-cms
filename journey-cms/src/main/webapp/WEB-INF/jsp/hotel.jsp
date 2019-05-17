<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>酒店列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 酒店管理 <span class="c-gray en">&gt;</span> 酒店列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 
		参数设置： 
			<input type="text" name=city"" id="city" placeholder="城市" style="width: 150px" class="input-text">
			<select id="level" name="level" class="input-text" style="width: 150px">
				<option>请选择酒店星级</option>
				<option value="五星级/豪华">五星级/豪华</option>
				<option value="四星级/高档">四星级/高档</option>
				<option value="三星级/舒适">三星级/舒适</option>
				<option value="二星级/经济">二星级/经济</option>
				<option value="客栈/公寓">客栈/公寓</option>
				<option value="其他">其他</option>
			</select>
			<input type="text" name="pageToken" id="pageToken" placeholder="页数" class="input-text" style="width: 120px;">
			<button  class="btn btn-success" type="button" onclick="hotelapi();">
				<i class="Hui-iconfont">&#xe665;</i> 从接口获取景点
			</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"><span class="r">共有数据：<strong>${count }</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="90">ID</th>
					<th width="120">酒店名称</th>
					<th width="110">品牌</th>
					<th width="70">城市</th>
					<th width="70">市区</th>
					<th width="120">星级</th>
					<th width="40">价格</th>
					<th width="60">评分</th>
					<th width="80">推荐状态</th>
					<th width="150">更新时间</th>
					<th width="40">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="hotelItem" items="${hotelList }">
				<tr class="text-c">
					<td>${hotelItem.id }</td>
					<td>${hotelItem.title }</td>
					<td>${hotelItem.brandName }</td>
					<td>${hotelItem.city }</td>
					<td>${hotelItem.district }</td>
					<td>${hotelItem.level }</td>
					<td>${hotelItem.minPrice }</td>
					<td>${hotelItem.rating }</td>
					<c:if test="${hotelItem.recommend eq 0 }">
						<td>未推荐</td>
					</c:if>
					<c:if test="${hotelItem.recommend eq 1 }">
						<td>主页推荐</td>
					</c:if>
					<c:if test="${hotelItem.recommend eq 2 }">
						<td>景点推荐</td>
					</c:if>
					<td>${hotelItem.updatetime }</td>
					<td class="td-manage">
					 <a style="text-decoration:none" class="ml-5" href="/hotel/${hotelItem.id }" title="编辑">
					 	<i class="Hui-iconfont">&#xe6df;</i>
					 </a> 
					 <a style="text-decoration:none" class="ml-5" onClick="hotelDel(this,'${hotelItem.id}')" href="javascript:;" title="删除">
						<i class="Hui-iconfont">&#xe6e2;</i>
					</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,8]}// 制定列不参与排序
	]
});

var page = ${page };
var maxPage = Math.ceil(${count }/10);
$("#DataTables_Table_0_previous").click(function () {
	if (page<=1) {
		return;
	}
	window.location.href = "/hotel?page=" + --page;
});
$("#DataTables_Table_0_next").click(function () {
	if (page>=maxPage) {
		return;
	}
	window.location.href = "/hotel?page=" + ++page;
});
//$(".current").click(function () {
//	$(".current").html(""+${page });
//});
$(document).ready(function() { 
	$(".current").html('${page }');
	$("#DataTables_Table_0_length").remove();
	$("#DataTables_Table_0_filter").remove();
}); 



function hotelDel(obj, id) {
	layer.confirm('确认要删除吗？', function(index) {
		$.ajax({
			type : 'GET',
			url : '/hotelDel/' + id,
			dataType : 'json',
			success : function(data) {
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			},
			error : function(data) {
				console.log(data.msg);
			},
		});
	});
}

//从接口获取景点
function hotelapi() {
    var city = $("#city").val();
    var level = $("#level").val();
    var pageToken = $("#pageToken").val();
	if (city == '' || pageToken == '' || level == '') {
		layer.msg('请输入正确的城市、酒店星级或页数！', {
			icon : 2,
			time : 1650
		});
		return;
	}
	layer.msg('正在添加，请稍等！', {
		icon : 0,
		time : 2000
	});
	$.ajax({
		url : '/hotelapi',
		method : 'get',
		data:{"city":city,"level":level,"pageToken":pageToken},
		ContentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : 'json',
		success : function(ret) {
			if (ret.status) {
				layer.msg(ret.msg, {
					icon : 1,
					time : 1500
				});
			}else{
				layer.msg(ret.msg, {
					icon : 2,
					time : 1500
				});
			}
		}
	});
}




</script>
</body>
</html>