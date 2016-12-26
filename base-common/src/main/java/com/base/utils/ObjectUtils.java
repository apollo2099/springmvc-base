/** 
  * Project Name:juanpi-order-ETL 
  * File Name:ObjectUtils.java 
  * Package Name:com.juanpi.order.etl.util 
  * Date:2016年10月9日下午2:45:27 
  * Copyright (c) 2016, JuanPi.com All Rights Reserved
  */  
  
package com.base.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/** 
  * 数据验证工具类 
  * @company:www.juanpi.com
  * @department:架构服务部/JAVA工程师
  * @author huixiong 
  * @date: 2016年10月9日 下午2:45:27 
  * @since:1.0.0
  */
public class ObjectUtils {
	/**
	 * 判断对象是否为空
	 * @author huixiong 
	 * @param o 输入参数
	 * @return 返回结果
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ((o instanceof String)) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if ((o instanceof Collection)) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if ((o instanceof Map)) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 判断对象是否非空
	 * @author huixiong 
	 * @param c 参数
	 * @return
	 */
	public static boolean isNotEmpty(Object c){
		return !isEmpty(c);
	}

}
  