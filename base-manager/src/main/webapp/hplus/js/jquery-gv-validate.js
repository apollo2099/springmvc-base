/**
 * Created by fangyh on 2015/4/21.
 */
$.validator.setDefaults({
    errorElement: "p",
    errorClass:"text-danger col-sm-3 p-error",
    errorPlacement: function(error, element) {                             //错误信息位置设置方法
        error.appendTo( element.parent().parent());                            //这里的element是录入数据的对象
    },
    highlight : function(element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    success : function(label) {
        label.closest('.form-group').removeClass('has-error');
        label.remove();
    },
    submitHandler: function(form) {
    	
    $(form).ajaxSubmit({
        type:"post",
        dataType:"json",
        success: function(responseText,statusText){
            if(true==responseText.status)
            {
                $(form).clearForm();
                if($(form).find("*[data-dismiss='modal']").length>0){
	                $(form).find("*[data-dismiss='modal']").click();
	                var callfn = $(form).attr("callfn");
	                if(callfn)
	                {
	                	eval(callfn+"()");
	                }
                }else{
                	$("#breadNav li:nth-last-child(2) > a").click();
                }
                layer.closeAll();
                /*
                BootstrapDialog.show({
					type: BootstrapDialog.TYPE_SUCCESS,
		            title: '操作结果提示',
		            message: responseText.msg || "操作成功!",
		            onshown: function(dialogRef){
		            	setTimeout(function() {
		            		dialogRef.close();
						}, 1000);
		            }
		        });
		        */
            }
            else{
                layer.msg(responseText.msg);
            }
        },
        error:function(XmlHttpRequest, textStatus, errorThrown){
        	BootstrapDialog.show({
				type: BootstrapDialog.TYPE_WARNING,
	            title: '操作结果提示',
	            message: dialogMng.htmlContent.warnTips.replace("#status", XmlHttpRequest.status)
	        });
        }
    }); }
});
jQuery.extend(jQuery.validator.messages, {
    required: "必选字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
    minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
    rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
    range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为{0} 的值"),
    min: jQuery.validator.format("请输入一个最小为{0} 的值")
});

jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    return this.optional(element) || length == 11 && /^1[358]\d{9}$/.test(value)
}, "请正确填写您的手机号码");
jQuery.validator.addMethod("commonString", function(value, element) {       
return this.optional(element) || /^[a-zA-Z0-9_@]+$/.test(value);       
}, "只能包含英文、数字、下划线、@等字符"); 
jQuery.validator.addMethod("stringCheck", function(value, element) {       
return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_@]+$/.test(value);       
}, "只能包含中文、英文、数字、下划线、@等字符"); 

jQuery.validator.addMethod("datetime", function(value, element) {
    var length = value.length;
    //alert(/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01]) (([0-1]?[0-9])|([2][0-3]))(:([0-5]?[0-9]))+$/.test(value));
    return this.optional(element) || /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01]) (([0-1]?[0-9])|([2][0-3]))(:([0-5]?[0-9]))+$/.test(value)
}, "日期时间不正确");
jQuery.validator.addMethod("greaterEquals",function(value,element,param){
	
    var startTime = $(param).val();
     var reg = new RegExp('-','g');
    startTime = startTime.replace(reg,'/');//正则替换
    endTime = value.replace(reg,'/');
    startTime = new Date(parseInt(Date.parse(startTime),10));
    endTime = new Date(parseInt(Date.parse(endTime),10));
    if(startTime>endTime){
        return false;
    }else{
        return true;
    }
},"结束日期必须大于开始日期");