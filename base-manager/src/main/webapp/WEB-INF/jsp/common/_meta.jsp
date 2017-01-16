<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="shortcut icon" href="favicon.ico">
<link href="${ctx }/hplus/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="${ctx }/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/hplus/css/animate.min.css" rel="stylesheet">
<link href="${ctx }/hplus/css/style.min.css?v=4.0.0" rel="stylesheet">
<link href="${ctx }/hplus/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
