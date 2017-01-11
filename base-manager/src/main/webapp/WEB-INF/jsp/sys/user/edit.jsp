<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>更新用户页面</title>

  <!--引入css文件-->
  <jsp:include page="../../common/_meta.jsp"></jsp:include>

</head>
<body class="white-bg">

<div class="ibox-content">
  <form class="form-horizontal m-t" id="userUpdateForm" callfn="refreshTable" action="/base-manager/user/edit" method="post">
   <input type="hidden" name="userId" value="${sysUser.userId}">
   <input type="hidden" name="ip" value="${sysUser.ip }" >
   <input type="hidden" name="password" value="${sysUser.password }" >
   <input type="hidden" name="lastLogin" value="${sysUser.lastLogin }" >
   
    <div class="form-group">
      <label class="col-sm-3 control-label">用户名：</label>
      <div class="col-sm-8">
        <input id="loginName" name="loginName" class="form-control"  value="${sysUser.loginName }" type="text" aria-required="true" aria-invalid="true" class="error">
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-3 control-label">昵称：</label>
      <div class="col-sm-8">
        <input id="name" name="name" class="form-control" type="text" value="${sysUser.name }">
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">用户状态：</label>
      <div class="col-sm-8">
       <select id="status" name="status" class="form-control">
         <option value="" >请选择</option>
         <option value="1" <c:if test="${sysUser.status==1 }">selected="selected"</c:if>>启用</option>
         <option value="0" <c:if test="${sysUser.status==0 }">selected="selected"</c:if>>冻结</option>
       </select>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">用户描述：</label>
      <div class="col-sm-8">
       <textarea rows="5" cols="60" id="description" name="description" class="form-control">${sysUser.description }</textarea>
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
            var e = "<i class='fa fa-times-circle'></i> ";
            $("#userUpdateForm").validate({
              rules: {
                loginName: {
                  required: !0,
                  minlength: 2
                },
                name: {
                  required: !0,
                  minlength: 2
                },
                status: "required"
              },
              messages: {
                loginName: {
                  required: e + "请输入您的用户名",
                  minlength: e + "用户名必须两个字符以上"
                },
                name: {
                  required: e + "请输入用户昵称",
                  minlength: e + "昵称必须两个字符以上"
                },
                status: {
                  required: e + "请设置用户状态"
                }
              }
            }),
                    $("#username").focus(function() {
                      var e = $("#firstname").val(),
                              r = $("#lastname").val();
                      e && r && !this.value && (this.value = e + "." + r)
                    })
          });

</script>
</body>
</html>

