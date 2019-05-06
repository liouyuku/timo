package com.linln.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Conver {

	
	public static <T> List<T> toBeanList(List<Object[]> content, Class<T> cls) {
		return ArrayToBean(cls,content);
	}

	@SuppressWarnings("unchecked")
	public static List ArrayToBean(Class cls, List<Object[]> objslist) {
		if (objslist.isEmpty())
			return null;
		List list = new ArrayList();
		for (Object[] objects : objslist) {

			Object bean = ObjectArrayToBean(cls, objects);
			list.add(bean);
		}
		return list;
	}

	public static <T> T ObjectArrayToBean(Class<T> cls, Object[] objs) {
		try {
			T t = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for(int i = 0; i < objs.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				field.set(t, objs[i]);
			}
			return t;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
