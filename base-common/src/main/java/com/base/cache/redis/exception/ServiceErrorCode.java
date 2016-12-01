package com.base.cache.redis.exception;

public class ServiceErrorCode {
    public final String				code;
    public final String				message;
    public final String				action;
    public final ServiceErrorCode	cause;

    public ServiceErrorCode(String code, String message, String action) {
        this(code, message, action, null);
    }

    public ServiceErrorCode(String code, String message, String action, ServiceErrorCode cause) {
        this.code = code;
        this.message = message;
        this.action = action;
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action;
    }

    public ServiceErrorCode getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return code + ":" + message + "," + action;
    }

    public static ServiceErrorCode UNKNOW_ERROR	= new ServiceErrorCode("S00001", "未知错误", "请查看错误信息");
    public static ServiceErrorCode CONFIGURE_ERROR	= new ServiceErrorCode("S00002", "配置信息读取错误", "请查看错误信息");
    public static ServiceErrorCode CACHE_HESSIAN_SERIALIZE_ERROR = new ServiceErrorCode("S00003", "序列化失败", "请检查序列化对象");
    public static ServiceErrorCode CACHE_KEY_NULL_ERROR = new ServiceErrorCode("S00004", "Cache服务key为空错误", "请检查put方法的key是否正确");
    public static ServiceErrorCode CACHE_VALUE_NULL_ERROR = new ServiceErrorCode("S00005", "Cache服务put方法的值为空", "请检查put方法的值是否正确");
    public static ServiceErrorCode CACHE_PUT_FAIL_ERROR = new ServiceErrorCode("S00006", "Cache服务put方法产生异常", "请查看错误信息");
    public static ServiceErrorCode CACHE_GET_FAIL_ERROR = new ServiceErrorCode("S00007", "Cache服务get方法产生异常", "请查看错误信息");
    public static ServiceErrorCode CACHE_DELETE_FAIL_ERROR = new ServiceErrorCode("S00008", "Cache服务delete方法产生异常", "请查看错误信息");
    public static ServiceErrorCode CACHE_PUTIFUNTOUCHED_FAIL_ERROR = new ServiceErrorCode("S00009", "Cache服务putIfUntouched方法产生异常", "请查看错误信息");
    public static ServiceErrorCode CACHE_INIT_FAIL_ERROR = new ServiceErrorCode("S00010", "Cache服务初始化失败", "请查看错误信息");
    public static ServiceErrorCode CACHE_ARGUMENT_NULL_ERROR = new ServiceErrorCode("S00011", "Cache服务参数为空错误", "请查看错误信息");
    public static ServiceErrorCode CACHE_ARGUMENT_TYPE_ERROR = new ServiceErrorCode("S00012", "Cache服务参数类型错误", "请查看错误信息");
    public static ServiceErrorCode CACHE_DESTROY_FAIL_ERROR = new ServiceErrorCode("S00013", "Cache服务销毁失败", "请查看错误信息");
    public static ServiceErrorCode CACHE_SQL_FAIL_ERROR = new ServiceErrorCode("S00014", "Cache服务执行SQL错误", "请查看错误信息");
    public static ServiceErrorCode CACHE_KEY_EMPTY_ERROR = new ServiceErrorCode("S00015", "Cache服务方法参数中key为空字符串", "请查看错误信息");
    public static ServiceErrorCode REDIS_EXECUTE_FAIL_ERROR = new ServiceErrorCode("S07001", "Redis服务执行错误", "请查看错误信息");
    public static ServiceErrorCode REDIS_CLIENT_NOT_INITIALIZED = new ServiceErrorCode("S07002", "Redis Client尚未初始化", "请查看错误信息");
}
