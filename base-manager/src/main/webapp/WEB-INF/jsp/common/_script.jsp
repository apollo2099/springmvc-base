<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx }/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/hplus/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${ctx }/hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx }/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx }/hplus/js/hplus.min.js?v=4.0.0"></script>
<script src="${ctx }/hplus/js/contabs.min.js" type="text/javascript"></script>
<script src="${ctx }/hplus/js/plugins/pace/pace.min.js"></script>


<script src="${ctx }/hplus/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${ctx }/hplus/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${ctx }/hplus/js/content.min.js?v=1.0.0"></script>

<script src="${ctx }/hplus/js/plugins/layer/layer.js"></script>
<!--验证js-->
<script src="${ctx }/hplus/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx }/hplus/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx }/hplus/js/demo/form-validate-demo.min.js"></script>
<script src="${ctx }/hplus/js/jquery-gv-validate.js"></script>
<script src="${ctx }/hplus/js/jquery.form.min.js"></script>


