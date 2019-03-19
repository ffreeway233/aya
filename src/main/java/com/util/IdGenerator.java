package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	
	
	
	private static AtomicInteger counter = new AtomicInteger(0);
	//全数字的唯一
	@SuppressWarnings("unused")
	public static String getNumberPrimaryKey(){
    	 if (counter.get() > 999999) {
             counter.set(1);
         }
         Long time = System.currentTimeMillis();
         Long returnValue = time * 100 + counter.incrementAndGet();
         return returnValue.toString();
    }
    
	public static String genPrimaryKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String genTradeNum() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = "" + System.currentTimeMillis();
		return str;
	}

	public static String genOrdersNum() {
		Random random = new Random();
		String s = random.nextInt(10000) + "";
		String str = System.currentTimeMillis() + s;
		return str;
	}

	public static String genOrdersNum2() {
		String str = "" + System.currentTimeMillis();
		return str;
	}

	/**
	 * 获取list最小值
	 * 
	 * @param ins
	 * @return
	 */
	public static Double getMin(List<Double> ins) {
		Object[] objs = ins.toArray();
		Arrays.sort(objs);
		return Double.valueOf(String.valueOf(objs[0]));
	}
}
