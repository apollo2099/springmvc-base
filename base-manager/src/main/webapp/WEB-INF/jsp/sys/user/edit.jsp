<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/11
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="ibox-content" style="width: 430px;">
  <form id="myForm" class="form-horizontal" autocomplete="off" data-validator-option="{theme:'default'}" method="post">
    <input type="hidden" name="id" value="$!{sysUser.userId}">
    <div class="form-group"><label class="col-sm-2 control-label">用户名</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${sysUser.loginName}" name="loginName" data-rule="用户名:required;loginName">
      </div>
    </div>
    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">登录密码</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" name="password" data-rule="登录密码:required;password">
      </div>
    </div>

    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">昵称</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${sysUser.name}" name="name" data-rule="用户名:required;name">
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">登录地址</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${sysUser.ip}" name="name" data-rule="用户名:required;ip">
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">最好登录时间</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${sysUser.lastLogin}" name="name" data-rule="用户名:required;lastLogin">
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">用户状态</label>
      <div class="col-sm-10">
        ${sysUser.status}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">用户角色</label>
      <div class="col-sm-10">
        <select class="form-control m-b" name="rid">

        </select>
      </div>
    </div>

    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">用户描述</label>
      <div class="col-sm-10">
        ${sysUser.description}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group">
      <div class="text-center">
        <button class="btn btn-primary" type="submit">提 交</button>
      </div>
    </div>
  </form>
</div>

<!--引入js文件-->
<jsp:include page="../../common/_script.jsp"></jsp:include>


<script type="text/javascript">
  $("#myForm").validator({
    valid: function(form){
      var me = this;
      // 提交表单之前，hold住表单，防止重复提交
      me.holdSubmit();
      $.ajax({
        url: "/base-manager/user/edit",
        data: $(form).serialize(),
        type: "POST",
        success: function(data){
          var d = JSON.parse(data);
          if(d.success && d.data){
            window.parent.location.reload();
          } else {
            me.holdSubmit(false);
          }
        }
      });
    }
  });
</script>
</body>
</html>

