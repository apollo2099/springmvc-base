package com.base.cache.redis.utils;

import com.base.utils.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取redis配置文件信息
 */
public class RedisConfig {

    private volatile static Map<String, String> defaultConfigs = new ConcurrentHashMap<String, String>();

    public void initConfig(){

        defaultConfigs.put("","");

    }

    public static String getConfig(String key, String defaultValue) {
        String value = defaultConfigs.get(key);
        return ObjectUtils.isEmpty(value) ? defaultValue : value;
    }

    public static String getConfig(String key) {
        return getConfig(key, null);
    }

    public static int getConfig(String key, int defaultValue) {
        String value = getConfig(key);
        return StringUtils.isBlank(value) ? defaultValue : Integer.parseInt(value);
    }
}