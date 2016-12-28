<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>H+ 后台主题UI框架 - 主页</title>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <jsp:include page="common/_meta.jsp"></jsp:include>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
         <jsp:include page="common/_menu.jsp"></jsp:include>
        <!--左侧导航结束-->

        <!--右侧部分开始-->
        <jsp:include page="index_context.jsp"></jsp:include>
        <!--右侧部分结束-->

        <!--右侧边栏开始-->
        <jsp:include page="index_right_sidebar.jsp"></jsp:include>
        <!--右侧边栏结束-->
        <!--mini聊天窗口开始-->
        <jsp:include page="index_mini.jsp"></jsp:include>
    </div>
    <!--引入js文件-->
    <jsp:include page="common/_script.jsp"></jsp:include>

</body>

</html>