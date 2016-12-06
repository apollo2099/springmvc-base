package com.base.cache.redis.spi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceFactoryFactory {
	public static <T> T getFactory(Class<T> factoryClass) {
		FactoryProvider<?> provider = LazyLoadProviderHolder.serviceFactories.get(factoryClass);
		if (provider == null) {
			throw new IllegalArgumentException(
					"No factory was registered for " + factoryClass.getCanonicalName());
		}

		return factoryClass.cast(provider.getFactoryInstance());
	}

	private static class LazyLoadProviderHolder {
		private static Map<Class<?>, FactoryProvider<?>> serviceFactories;

		static {
			Map<Class<?>, FactoryProvider<?>> temp = new HashMap<Class<?>, FactoryProvider<?>>();
			@SuppressWarnings("rawtypes")
			List<FactoryProvider> factoryProviderList = ServiceLoaderUtils.getServices(
					FactoryProvider.class, ServiceFactoryFactory.class.getClassLoader());
			for (FactoryProvider<?> provider : factoryProviderList) {
				FactoryProvider<?> previous = temp.get(provider.getBaseInterface());

				if (temp.containsKey(provider.getBaseInterface())) {
					throw new IllegalStateException("Ambiguous providers: " + provider.getClass().getCanonicalName() +
							" versus " + previous.getClass().getCanonicalName());
				}
				temp.put(provider.getBaseInterface(), provider);
			}
			serviceFactories = temp;
		}
	}
}
