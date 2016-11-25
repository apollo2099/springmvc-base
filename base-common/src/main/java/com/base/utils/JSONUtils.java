package com.base.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <pre>
 * Json格式化工具类
 * </pre>
 *
 * @className JsonUtil.java
 * @author huixiong
 * @since 1.0.0
 * @date 2015年8月27日 上午10:16:47
 */
public class JSONUtils {
	/**
	 * 将实体对象转换为String
	 * @param object
	 * @return
	 */
	public  static String jsonToString(Object object){
		return JSONObject.toJson(object);

	}


	public static Object jsonTo(){
		JSONObject.parse();
	}

	/**
	 * 将Json数据转换为对象
	 * @param jsonStr
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObject(String jsonStr, Class<T> clazz){        
		JSONObject json = JSONObject.parseObject(jsonStr);
		return JSONObject.toJavaObject(json, clazz);
    }



}
