package com.charles.common.constants.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanToMapUtil {
	
	/**
	 * 将一个Map对象转化为一个JavaBean
	 * 
	 * @param type		要转化的类型
	 * @param map		包含属性值的map
	 * @return			转化出来的JavaBean对象
	 * @throws Exception
	 */
	public static Object convertMap(Class type, Map map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for(int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			
			if(map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				
				Object[] args = new Object[1];
				args[0] = value;
				
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}
	
	
	public static Map convertBean(Object bean) throws Exception {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for(int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if(!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = null;
				try{
					result = readMethod.invoke(bean, new Object[0]);
				} catch(Exception e) {
				}
				returnMap.put(propertyName, result);
			}
		}
		return returnMap;
	}
}
