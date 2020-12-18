<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- <%@taglib uri="spring.tld" prefix="spring"%> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>考试管理</title>
<meta name="keywords" content="">
<link rel="shortcut icon"
	href="<%=basePath%>resources/images/favicon.ico" />
<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
<link href="resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<link href="resources/css/exam.css" rel="stylesheet">
<link href="resources/chart/morris.css" rel="stylesheet">
<style>
.change-property, .publish-paper, .delete-paper, .offline-paper {
	cursor: pointer;
}
</style>
</head>
<body>
	<header>
		<div class="container">
			<div class="row">
				<div class="col-xs-5">
					<div class="logo">
						<h1>
							<a href="#">网站管理系统</a>
						</h1>
						<div class="hmeta">专注互联网在线考试解决方案</div>
					</div>
				</div>
				<div class="col-xs-7" id="login-info">
					<c:choose>
						<c:when
							test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
							<div id="login-info-user">

								<a
									href="user-detail/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"
									id="system-info-account" target="_blank">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</a>
								<span>|</span> <a href="j_spring_security_logout"><i
									class="fa fa-sign-out"></i> 退出</a>
							</div>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary" href="user-register">用户注册</a>
							<a class="btn btn-success" href="user-login-page">登录</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</header>
	<!-- Navigation bar starts -->

	<div class="navbar bs-docs-nav" role="banner">
		<div class="container">
			<nav class="collapse navbar-collapse bs-navbar-collapse"
				role="navigation">
				<c:import
					url="/common-page/top-menu?topMenuId=${topMenuId}&leftMenuId=${leftMenuId}"
					charEncoding="UTF-8" />
			</nav>
		</div>
	</div>

	<!-- Navigation bar ends -->

	<!-- Slider starts -->

	<div>
		<!-- Slider (Flex Slider) -->

		<div class="container" style="min-height: 800px;">

			<div class="row">
				<div class="col-xs-2" id="left-menu">
					<c:import
						url="/common-page/left-menu?topMenuId=${topMenuId}&leftMenuId=${leftMenuId}"
						charEncoding="UTF-8" />
				</div>
				<div class="col-xs-10" id="right-content">
					<iframe id="mainIframe" name="mainIframe" src="user/get?userId=${userId}"  frameborder="0" scrolling="auto" ></iframe>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="copy">
						<p>
							ExamStack Copyright © <a href="http://www.examstack.com/" target="_blank">ExamStack</a>
						</p>
					</div>
				</div>
			</div>

		</div>

	</footer>
	<!-- Slider Ends -->

	<!-- Javascript files -->
	<!-- jQuery -->
	<script type="text/javascript"
		src="resources/js/jquery/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="resources/js/all.js"></script>
	<!-- Bootstrap JS -->
	<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="resources/js/exam-list.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".link-user-r-btn").click(function(){
				$("#link-user-btn").data("id",$(this).data("id"));
				$("#link-user-modal").modal({
					backdrop : true,
					keyboard : true
				});
			});
		});
	</script>
</body>
</html>