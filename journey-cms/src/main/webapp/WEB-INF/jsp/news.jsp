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
<title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 参数设置：
		<select id="catLabel2" name="catLabel2" class="input-text" style="width: 150px">
			<option>请选择咨询类型</option>
			<option value="建筑">建筑</option>
			<option value="旅游产业">旅游产业</option>
			<option value="自驾游">自驾游</option>
			<option value="摄影">摄影</option>
			<option value="小吃">小吃</option>
			<option value="手工艺">手工艺</option>
		</select>
		<input type="text" name=size"" id="size" placeholder="数量，推荐100条以上" style="width: 150px" class="input-text">
		<button class="btn btn-success" onclick="newsapi();"><i class="Hui-iconfont">&#xe665;</i>从接口获取资讯</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l"><a href="javascript:;" id="batchDel"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> </span>
	  <span class="r">共有数据：<strong>${count }</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="40"><input name="" type="checkbox" value=""></th>
					<th width="150">ID</th>
					<th width="130">标题</th>
					<th width="300">URL</th>
					<th width="50">资讯类型</th>
					<th width="120">更新时间</th>
					<th width="70">推荐状态</th>
					<th width="50">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="newsItem" items="${newsList }">
				<tr class="text-c">
					<td><input name="" type="checkbox" value="${newsItem.id }"></td>
					<td>${newsItem.id }</td>
					<td>${newsItem.title }</td>
					<td><a target="_blank" href="${newsItem.url }">${newsItem.url }</a></td>
					<td>${newsItem.catLabel2 }</td>
					<td>${newsItem.updatetime }</td>
					<c:if test="${newsItem.recommend eq 0 }">
		                <td>未推荐</td>
		            </c:if>
		            <c:if test="${newsItem.recommend eq 1 }">
		                <td>酒店列表推荐</td>
		            </c:if>
		            <c:if test="${newsItem.recommend eq 2 }">
		                <td>酒店查找</td>
		            </c:if>
		            <c:if test="${newsItem.recommend eq 3 }">
		                <td>酒店详情推荐</td>
		            </c:if>
		            <c:if test="${newsItem.recommend eq 4 }">
		                <td>景点详情推荐</td>
		            </c:if>
					
		            <td class="td-manage">
		                 <a style="text-decoration:none" class="ml-5" href="/news/${newsItem.id }" title="编辑">
		                     <i class="Hui-iconfont">&#xe6df;</i>
		                 </a> 
		                 <a style="text-decoration:none" class="ml-5" onClick="newsDel(this,'${newsItem.id }')" href="javascript:;" title="删除">
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
	"aaSorting": [[ 5, "desc" ]],//默认第几个排序
	"aoColumnDefs": [
	  //{"bVisible": true, "aTargets": [ 7,8 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
	]
});

function newsDel(obj, id) {
	layer.confirm('确认要删除吗？', function(index) {
		$.ajax({
			type : 'GET',
			url : '/newsDel/' + id,
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

//从接口获取资讯
function newsapi() {
  var size = $("#size").val();
  var catLabel2 = $("#catLabel2").val();
	if (size == '' || catLabel2 == '') {
		layer.msg('请输入正确的参数！', {
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
		url : '/newsapi',
		method : 'get',
		data:{"size":size,"catLabel2":catLabel2},
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




var page = ${page };
var maxPage = Math.ceil(${count }/10);
$("#DataTables_Table_0_previous").click(function () {
	if (page<=1) {
		return;
	}
	window.location.href = "/news?page=" + --page;
});
$("#DataTables_Table_0_next").click(function () {
	if (page>=maxPage) {
		return;
	}
	window.location.href = "/news?page=" + ++page;
});
//$(".current").click(function () {
//	$(".current").html(""+${page });
//});
$(document).ready(function(){ 
	$(".current").html('${page }');
	$("#DataTables_Table_0_length").remove();
	$("#DataTables_Table_0_filter").remove();
}); 


$('#batchDel').click(function(){
	 layer.confirm('确认要删除吗？', function(index) {
       var str = "";
       $('table input:checkbox:gt(0)').each(function(){
           if(this.checked==true){
               str += this.value + ',';
           }
       })

       $.ajax({
			type : 'GET',
			url : '/newssDel/' + str,
			dataType : 'json',
			success : function(data) {
				layer.msg('删除成功，请刷新!', {
					icon : 1,
					time : 1000
				});
			},
			error : function(data) {
				console.log(data.msg);
			},
		});
	 })
   })

</script>
</body>
</html>