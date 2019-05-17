<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		用户管理 <span class="c-gray en">&gt;</span> 用户列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a class="btn btn-primary radius"
				 href="/addPlaces"><i
					class="Hui-iconfont">&#xe600;</i> 添加景点</a></span> <span class="r">共有数据：<strong>${count }</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr>
						<th scope="col" colspan="9">用户列表</th>
					</tr>
					<tr class="text-c">
						<th width="40">ID</th>
						<th width="150">用户名</th>
						<th width="150">手机号码</th>
						<th width="130">注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="userItem" items="${userList }">
					<tr class="text-c">
						<td>${userItem.id }</td>
			            <td>${userItem.username }</td>
			            <td>${userItem.phone }</td>
			            <td>${userItem.createTime }</td>
				     </tr>
				    </c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting" : [ [ 3, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false
			} // 制定列不参与排序
			]
		});
		var page = ${page };
		var maxPage = Math.ceil(${count }/10);
		$("#DataTables_Table_0_previous").click(function () {
			if (page<=1) {
				return;
			}
			window.location.href = "/user?page=" + --page;
		});
		$("#DataTables_Table_0_next").click(function () {
			if (page>=maxPage) {
				return;
			}
			window.location.href = "/user?page=" + ++page;
		});
		//$(".current").click(function () {
		//	$(".current").html(""+${page });
		//});
		$(document).ready(function() { 
			$(".current").html('${page }');
			$("#DataTables_Table_0_length").remove();
			$("#DataTables_Table_0_filter").remove();
		}); 


	</script>
</body>
</html>