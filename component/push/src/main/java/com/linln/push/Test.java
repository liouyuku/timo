package com.linln.push;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;

@SuppressWarnings("rawtypes")
public class Test {
	public static void main(String[] args) {
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] arrays = new Object[] { "aaa", "bbbb", "cccc" };
		Object[] arrays1 = new Object[] { "aaa111", "bbbb111", "cc111cc" };
		Object[] arrays2 = new Object[] { "2222aaa", "bb222bb", "ccc222c" };
		list.add(arrays);
		list.add(arrays1);
		list.add(arrays2);
		List<A> as = new ArrayList<A>();


		List s = ArrayToBean(A.class, list);

		System.out.println("jieshu");

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
			for (int i = 0; i < fields.length; i++) {
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
