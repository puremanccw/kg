package com.charles.common.constants.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 将一些常用的数据库对象放到threadLoacl中做缓存
 * @author puremancw
 *
 */
public class ThreadCacheUtil {
	
	public static final ThreadLocal<Map<String, Object>> threadCacheHolder = new ThreadLocal();
	
	public static void put(String key, Object obj) {
		Map<String, Object> threadCache = threadCacheHolder.get();
		
		if(threadCache == null) {
			threadCache = new HashMap<String, Object>();
		}
		
		threadCache.put(key, obj);
		
		threadCacheHolder.set(threadCache);
	}
	
	public static Object get(String key) {
		Map<String, Object> threadCache = threadCacheHolder.get();
		
		if(threadCache == null) {
			return null;
		}
		
		return threadCache.get(key);
	}
	
	
	public static void clean() {
		Map<String, Object> threadCache = threadCacheHolder.get();
		
		if(threadCache != null) {
			threadCacheHolder.get().clear();
		}
		
		threadCacheHolder.remove();
	}
}
