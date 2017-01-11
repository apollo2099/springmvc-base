<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 数据表格</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--引入css文件-->
    <jsp:include page="../../common/_meta.jsp"></jsp:include>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox-content text-right form-inline" style="background: #F3F3F4;">
            <div class="input-group">
                <input type="text" class="form-control">
			<span class="input-group-btn">
				<button type="button" class="btn btn-info">搜索</button>
			</span>
            </div>
            <button class="btn btn-primary" onclick="addUser()" type="button">
                <i class="fa fa-pencil"></i>&nbsp;添加用户</button>
        </div>
    </div>


    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户列表</h5>
                    <div class="ibox-tools">
                    
                    </div>
                </div>
                <div class="ibox-content">
                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>登录账户</th>
                            <th>密码</th>
                            <th>昵称</th>
                            <th>最后登录时间</th>
                            <th>最后登录IP</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

</div>

<!--引入js文件-->
<jsp:include page="../../common/_script.jsp"></jsp:include>

<!--分页插件数据-->
<script>
    var defTable = $('.dataTables-example').dataTable(
            {
                "sErrMode" : "alert",
                "sPaginationType": "full_numbers", //分页风格，full_number会把所有页码显示出来（大概是，自己尝试）
                "sDom": "<'row-fluid inboxHeader'<'span6'<'dt_actions'>l><'span6'f>r>t<'row-fluid inboxFooter'<'span6'i><'span6'p>>", //待补充
                "iDisplayLength": 10,//每页显示10条数据
                "bAutoWidth": false,//宽度是否自动，感觉不好使的时候关掉试试
                "bLengthChange": false,
                "bFilter": false,
                "oLanguage": {//下面是一些汉语翻译
                    "sSearch": "搜索",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                    "sProcessing": "<img src=''/>", //这里是给服务器发请求后到等待时间显示的 加载gif
                    "oPaginate":
                    {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "末页"
                    }
                },
                "bProcessing": true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
                "bServerSide": true, //开启服务器模式，使用服务器端处理配置datatable。注意：sAjaxSource参数也必须被给予为了给datatable源代码来获取所需的数据对于每个画。 这个翻译有点别扭。开启此模式后，你对datatables的每个操作 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值。
                "sAjaxSource": "/base-manager/user/pageList", //给服务器发请求的url
                "createdRow" : function(row, mData, index) {
                    //$('td:eq(0)', row).html("<input type='hidden' name='chx_default' value='" + mData.userId + "'/>");
                },
                "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
                    {"mData": 'userId'},
                    {"mData": 'loginName'},
                    {"mData": 'password'}, //mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
                    {"mData": 'name'},
                    {"mData": 'lastLogin'},
                    {"mData": 'ip'}
                ],
                "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                    {sDefaultContent: '',aTargets: [ '_all' ]},
                    {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                    //{"bSearchable": false, "aTargets": [1, 2, 3, 4, 5, 6]}, //bSearchable 这个属性表示是否可以全局搜索，其实在服务器端分页中是没用的
                ],
                "aaSorting": [[2, "desc"]], //默认排序
                "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
                    var statusHtml="";
                    statusHtml+='<a class="btn btn-xs btn-info" onclick="detailUser(' + "'" + aData.userId + "'" + ')"><i class="fa fa-link"></i>详情</a>';
                    statusHtml+='<a class="btn btn-xs btn-warning" onclick="editUser(' + "'" + aData.userId + "'" + ')"><i class="fa fa-pencil"></i>编辑</a>';
                    statusHtml+='<a class="btn btn-xs btn-danger" onclick="delUser(' + "'" + aData.userId + "'" + ')"><i class="fa fa-remove"></i>删除</a>';
                    $('td:eq(6)', nRow).html(statusHtml);
                    return nRow;
                },
                "fnServerParams": function(aoData) {
                    aoData.push({"name": "loginName","value": $("#loginName").val()});
                    aoData.push({"name": "startTime","value": $("#startTime").val()});
                    aoData.push({"name": "endTime","value": $("#endTime").val()});
                },
                "fnInitComplete": function(oSettings, json) { //表格初始化完成后调用 在这里和服务器分页没关系可以忽略
                    $("input[aria-controls='DataTables_Table_0']").attr("placeHolder", "请输入高手用户名");
                }
            }
    );


    function refreshTable(toFirst) {
        if(toFirst){//表格重绘，并跳转到第一页
            defTable.fnDraw();
        }else{//表格重绘，保持在当前页
            defTable.fnDraw(false);
        }
    }


    function detailUser(userId){
        var title = "用户详情";
        layer.msg(title);
        var url = "/base-manager/user/detail/" + userId;
        layer.msg(url);
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.8,
            area: ['800px', '630px'],
            fix: false,
            maxmin: true,
            content: url
        });
    }

    function delUser(userId) {
        //询问框
        layer.confirm('确定删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.ajax({
                type: "POST",
                url: "/base-manager/user/delete/" + userId,
                datatype: "text",
                success: function (data) {
                    if(data == 'true'){
                        refreshTable();
                    }
                    layer.closeAll();
                }
            });
        }, function () {
            return;
        });
    }

    function editUser(userId){
        var title = "新增用户";
        var url = "/base-manager/user/edit";
        if(userId!=null){
            title = "编辑用户";
            url = url + "/" + userId;
        }
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.8,
            area: ['800px', '630px'],
            fix: false,
            maxmin: true,
            content: url
        });
    }


    function addUser(){
        var title = "新增用户";
        var url = "/base-manager/user/add";
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.8,
            area: ['600px', '430px'],
            fix: false,
            maxmin: true,
            content: url
        });
    }
</script>
</body>

</html>