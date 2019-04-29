package com.linln.common.utils;

import java.util.ArrayList;
import java.util.List;



/**
 * 生成期数，栋数编号
 * @author Administrator
 *
 */
public class GenerateNumberUtil {

	public GenerateNumberUtil() {
		
	}
public static List<String> GenerateNumber(){
	List<String> list= new ArrayList<String>();
	for(int i=1;i<100;i++){
		if(i<10){
			list.add("0"+i);
			continue;
		}
		list.add(Integer.toString(i));
	}
	return list;
}

}
