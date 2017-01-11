<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>用户详情</title>
  <!--引入css文件-->
  <jsp:include page="../../common/_meta.jsp"></jsp:include>

</head>
<body class="white-bg">
<div class="ibox-content">
  <form id="myForm" class="form-horizontal" autocomplete="off" data-validator-option="{theme:'default'}">
    <input type="hidden" name="id" value="$!{user.id}">
    <div class="form-group"><label class="col-sm-2 control-label">登录名称</label>
      <div class="col-sm-10">
        ${sysUser.loginName}
      </div>
    </div>
    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">登录密码</label>
      <div class="col-sm-10">
        ${sysUser.password}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">昵称</label>
      <div class="col-sm-10">
        ${sysUser.name}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">登录地址</label>
      <div class="col-sm-10">
        ${sysUser.ip}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">最好登录时间</label>
      <div class="col-sm-10">
        ${sysUser.lastLogin}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">用户状态</label>
      <div class="col-sm-10">
        ${sysUser.status}
      </div>
    </div>


    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">用户描述</label>
      <div class="col-sm-10">
        ${sysUser.description}
      </div>
    </div>

    <div class="hr-line-dashed"></div>
    <div class="form-group"><label class="col-sm-2 control-label">用户角色</label>
      <div class="col-sm-10">
        <select class="form-control m-b" name="rid">

        </select>
      </div>
    </div>
  </form>
</div>


<!--引入js文件-->
<jsp:include page="../../common/_script.jsp"></jsp:include>
</body>
</html>
