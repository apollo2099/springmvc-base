<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>H+ 后台主题UI框架 - 注册</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="/base-manager/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/base-manager/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/base-manager/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/base-manager/hplus/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎注册 H+</h3>
            <p>创建一个H+新账户</p>
            <form class="m-t" role="form" action="/base-manager/user/register" method="post" target="_self">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="请输入用户名" name="loginName" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="请输入密码" name="password" required="">
                </div>
     
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="请再次输入密码" name="password2" required="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="昵称" name="name" required="">
                </div>
                <div class="form-group text-left">
                    <div class="checkbox i-checks">
                        <label class="no-padding">
                            <input type="checkbox"><i></i> 我同意注册协议</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">注 册</button>

                <p class="text-muted text-center"><small>已经有账户了？</small><a href="/base-manager/user/tologin">点此登录</a>
                </p>

            </form>
        </div>
    </div>
    <script src="/base-manager/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/base-manager/hplus/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="/base-manager/hplus/js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>