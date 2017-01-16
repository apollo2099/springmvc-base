<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>新增用户页面</title>

  <!--引入css文件-->
  <jsp:include page="../../common/_meta.jsp"></jsp:include>

</head>
<body class="white-bg">

<div class="ibox-content">
  <form class="form-horizontal m-t" id="signupForm2" action="/base-manager/user/register" method="post">
    <div class="form-group">
      <label class="col-sm-3 control-label">用户名：</label>
      <div class="col-sm-8">
        <input id="loginName" name="loginName" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">密码：</label>
      <div class="col-sm-8">
        <input id="password" name="password" class="form-control" type="password">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">确认密码：</label>
      <div class="col-sm-8">
        <input id="confirm_password" name="confirm_password" class="form-control" type="password">
        <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">昵称：</label>
      <div class="col-sm-8">
        <input id="name" name="name" class="form-control" type="text">
      </div>
    </div>


    <div class="form-group">
      <div class="col-sm-8 col-sm-offset-3">
        <div class="checkbox">
          <label>
            <input type="checkbox" class="checkbox" id="agree" name="agree"> 我已经认真阅读并同意《H+使用协议》
          </label>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-8 col-sm-offset-3">
        <button class="btn btn-primary" type="submit">提交</button>
      </div>
    </div>
  </form>
</div>

<!--引入js文件-->
<jsp:include page="../../common/_script.jsp"></jsp:include>


<script type="text/javascript">

  $.validator.setDefaults({
    highlight: function(e) {
      $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
    },
    success: function(e) {
      e.closest(".form-group").removeClass("has-error").addClass("has-success")
    },
    errorElement: "span",
    errorPlacement: function(e, r) {
      e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"
  }),
          $().ready(function() {
            $("#commentForm").validate();
            var e = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm2").validate({
              rules: {
                loginName: {
                  required: !0,
                  minlength: 2
                },
                password: {
                  required: !0,
                  minlength: 5
                },
                confirm_password: {
                  required: !0,
                  minlength: 5,
                  equalTo: "#password"
                },
                name: {
                  required: !0,
                  minlength: 2
                },
                agree: "required"
              },
              messages: {
                loginName: {
                  required: e + "请输入您的用户名",
                  minlength: e + "用户名必须两个字符以上"
                },
                password: {
                  required: e + "请输入您的密码",
                  minlength: e + "密码必须5个字符以上"
                },
                confirm_password: {
                  required: e + "请再次输入密码",
                  minlength: e + "密码必须5个字符以上",
                  equalTo: e + "两次输入的密码不一致"
                },
                agree: {
                  required: e + "必须同意协议后才能注册",
                  element: "#agree-error"
                }
              }
            }),
            $("#username").focus(function() {
             var e = $("#firstname").val(), r = $("#lastname").val();
              e && r && !this.value && (this.value = e + "." + r)
            }),
                    success:"valid",
                    submitHandler:function(form){
              $(form).ajaxSubmit();
              var index = parent.layer.getFrameIndex(window.name);
              parent.$('.btn-refresh').click();
              parent.location.reload();
              parent.layer.close(index);
            }
          });

</script>

</body>
</html>

