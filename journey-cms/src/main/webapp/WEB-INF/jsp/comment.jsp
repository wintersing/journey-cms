<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>评论列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		评论管理 <span class="c-gray en">&gt;</span> 评论列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" id="commentsDel"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> </span> <span class="r">共有数据：<strong>${count }</strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="40"><input name="" type="checkbox" value=""></th>
						<th width="80">ID</th>
						<th width="110">评论者名称</th>
						<th width="80">评论类型</th>
						<th width="150">评论时间</th>
						<th width="内容">评论内容</th>
						<th width="40">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="commentItem" items="${commentList }">
						<tr class="text-c">
							<td><input name="" type="checkbox" value="${commentItem.id }"></td>
							<td>${commentItem.id }</td>
							<td>${commentItem.commenterScreenName }</td>
							<c:if test="${commentItem.parent eq 'sight' }">
								<td>景点评论</td>
							</c:if>
							<c:if test="${commentItem.parent eq 'post' }">
								<td>游记评论</td>
							</c:if>
							<td><jsp:useBean id="timestamp" class="java.util.Date" /> <jsp:setProperty
									name="timestamp" property="time"
									value="${commentItem.publishDate}" /> <fmt:formatDate
									value="${timestamp}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${commentItem.content }</td>
							<td class="td-manage"><a style="text-decoration: none"
								class="ml-5" onClick="commentDel(this,'${commentItem.id }')"
								href="javascript:;" title="删除"> <i class="Hui-iconfont">&#xe6e2;</i>
							</a></td>
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
	<script type="text/javascript"
		src="/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
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
			window.location.href = "/comment?page=" + --page;
		});
		$("#DataTables_Table_0_next").click(function () {
			if (page>=maxPage) {
				return;
			}
			window.location.href = "/comment?page=" + ++page;
		});
		//$(".current").click(function () {
		//	$(".current").html(""+${page });
		//});
		$(document).ready(function() { 
			$(".current").html('${page }');
			$("#DataTables_Table_0_length").remove();
			$("#DataTables_Table_0_filter").remove();
		}); 



		function commentDel(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$.ajax({
					type : 'GET',
					url : '/commentDel/' + id,
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
		

		 $('#commentsDel').click(function(){
			 layer.confirm('确认要删除吗？', function(index) {
	            var str = "";
	            $('table input:checkbox:gt(0)').each(function(){
	                if(this.checked==true){
	                    str += this.value + ',';
	                }
	            })

	            $.ajax({
					type : 'GET',
					url : '/commentsDel/' + str,
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