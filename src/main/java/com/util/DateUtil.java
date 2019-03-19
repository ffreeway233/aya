package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	public static Date formatString(String str, String format) throws Exception {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDay() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * yyyy-MM
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentYearMonth() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
	/**
	 * yyyy
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentYear() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	/**
	 * MM
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentMonth() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(date);
	}
	/**
	 * dd
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentNowday() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(date);
	}
	/**
	 * 判断日期是否合法
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static boolean isValidDate(String str, String format) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			sdf.setLenient(false);
			sdf.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 判读是否超时
	 * 
	 * @param datetime开始时间
	 * @param endtime毫秒时间数
	 * @return int 1为超时，2为未超时，3为异常
	 */
	public static int isOuttime(String datetime, long endtime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = df.parse(DateUtil.getCurrentDateStr());
			String start = datetime;
			d2 = df.parse(start);
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			long end = endtime;// 24 * 60 * 60 * 1000;// 超过1天为过期
			if (diff - end >= 0) {
				// 超过时间
				return 1;
			} else {
				// 未超过时间
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		} finally {
			df = null;
			d1 = null;
			d2 = null;
		}
	}

	/**
	 * 判断stime是否在etime之前
	 * 
	 * @param stime
	 * @param etime
	 * @return 1在前， 2不在前， 3异常
	 * @throws ParseException
	 */
	public static int checkTime(String stime, String etime, String dateformat) throws ParseException {
		Date sDate = null, eDate = null;
		try {
			sDate = new SimpleDateFormat(dateformat).parse(stime);
			eDate = new SimpleDateFormat(dateformat).parse(etime);
			if (sDate.before(eDate)) {
				// s在前
				return 1;
			} else {
				// s不在前
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		} finally {
			sDate = null;
			eDate = null;
		}
	}

	/**
	 * 判断now是否在某个时间范围内
	 * 
	 * @param stime
	 * @param etime
	 * @param now
	 * @return 1在s前,2范围内,3在e后,4异常
	 * @throws ParseException
	 */
	public static int checkTime(String stime, String etime, String now, String dateformat) throws ParseException {
		Date sDate = null, eDate = null, nDate = null;
		try {
			sDate = new SimpleDateFormat(dateformat).parse(stime);
			eDate = new SimpleDateFormat(dateformat).parse(etime);
			nDate = new SimpleDateFormat(dateformat).parse(now);
			if (nDate.before(sDate)) {
				// 未开始，在s前
				// System.out.println("1true");
				return 1;
			} else if (nDate.after(eDate) || nDate.equals(eDate)) {
				// 已过期，在e后
				// System.out.println("2true");
				return 3;
			} else {
				// 进行中，范围内
				// System.out.println("3true");
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
		} finally {
			sDate = null;
			eDate = null;
			nDate = null;
		}
	}
}
