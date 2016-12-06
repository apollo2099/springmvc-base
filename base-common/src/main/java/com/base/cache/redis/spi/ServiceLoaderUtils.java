package com.base.cache.redis.spi;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ServiceLoaderUtils {
    public static <T> List<T> getServices(final Class<T> clazz, final ClassLoader classLoader) {
        return AccessController.doPrivileged(new PrivilegedAction<List<T>>() {
            @Override
            public List<T> run() {
                List<T> result = new ArrayList<T>();
                ServiceLoader<T> providers = ServiceLoader.load(clazz, classLoader);
                if (providers != null) {
                    for (T provider : providers) {
                        result.add(provider);
                    }
                }
                return result;
            }
        });
    }
}