package com.linln;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;

public class Test {

	

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] arrays = new Object[]{"aaa","bbbb","cccc"};
		Object[] arrays1 = new Object[]{"aaa111","bbbb111","cc111cc"};
		Object[] arrays2 = new Object[]{"2222aaa","bb222bb","ccc222c"};
		list.add(arrays);
		list.add(arrays1);
		list.add(arrays1);
		List<A> as = new ArrayList<A>();
		for (Object[] objs : list) {
			A a = new A();
			for (int i = 0; i < objs.length; i++) {
				Object o = objs[i];
				BeanUtil.setFieldValue(a, i + "", o);
			}
			as.add(a);
		}
		
		System.out.println("jieshu");
		/*Class studentclass=Student.class;
		Method[] methods = studentclass.getMethods();
		Object newInstance = studentclass.newInstance();
		for(Method method:methods){
			if(method.getName().contains("set")){
				method.invoke(newInstance, 1);
				System.out.println(method.getName());
			}
		}
		System.out.println((Student)newInstance);*/

	}

}
