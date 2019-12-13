package com.ztkj.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author yup
 * 2018年3月18日
 */
public class DateUtil {
	
	//日期格式
	public static final String DATE_LONG = "yyyy-MM-dd";
	public static final String DATE_SHORT = "yyyyMMdd";
	public static final String TIME_LONG = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_SHORT = "yyyyMMddHHmmss";
	public static final String TIMESTAMP_LONG = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String TIMESTAMP_SHORT = "yyyyMMddHHmmssSSS";
	
	//时间单位
	public static final int YEAR = 1;
	public static final int MONTH = 2;
	public static final int DAY = 3;
	public static final int HOUR = 4;
	public static final int MINUTE = 5;
	public static final int SECOND = 6;
	
	
	/**
	 * 获取当前时间字符串表示
	 * @author yup
	 * 2018年3月18日
	 * @param format    时间格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 字符串转时间
	 * @author yup
	 * 2018年3月18日
	 * @param dateStr   时间字符串
	 * @param format   时间格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date stringToDate(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 时间转字符串
	 * @author yup
	 * 2018年3月18日
	 * @param date
	 * @param format  时间格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转sql时间
	 * @author yup
	 * 2018年3月18日
	 * @param dateStr   时间字符串
	 * @param format   时间格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static java.sql.Date stringToSqlDate(String dateStr, String format) {
		Date date = stringToDate(dateStr, format);
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * sql时间转字符串
	 * @author yup
	 * 2018年3月18日
	 * @param date
	 * @param format  时间格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String sqlDateToString(java.sql.Date date, String format) {
		return dateToString(date, format);
	}
	
	/**
	 * 字符串转Timestamp
	 * @author yup
	 * 2018年3月18日
	 * @param dateStr   时间字符串
	 * @return
	 */
	public static Timestamp stringToTimestamp(String dateStr) {
		return Timestamp.valueOf(dateStr);
	}
	
	/**
	 * Timestamp转字符串
	 * @author yup
	 * 2018年3月18日
	 * @param date
	 * @param format  时间格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String timestampToString(Timestamp date, String format) {
		return dateToString(date, format);
	}

	/**
	 * 日期的加减
	 * @author yup
	 * 2018年3月18日
	 * @param date           时间
	 * @param timeUnit    时间单位：1-年、2-月、3-日、4-小时、5-分钟、6-秒
	 * @param amount     日期加减量
	 * @return
	 */
	public static Date getAddDate(Date date, int timeUnit, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);  //时间转日历
		switch(timeUnit) {
			case YEAR :
				c.add(Calendar.YEAR, amount);
				break;
			case MONTH :
				c.add(Calendar.MONTH, amount);
				break;
			case DAY :
				c.add(Calendar.DAY_OF_MONTH, amount);
				break;
			case HOUR :
				c.add(Calendar.HOUR_OF_DAY, amount);
				break;
			case MINUTE :
				c.add(Calendar.MINUTE, amount);
				break;
			case SECOND :
				c.add(Calendar.SECOND, amount);
				break;
		}
		return c.getTime();
	}
	
	/**
	 * 计算两个日期相差多少时间
	 * @author yup
	 * 2018年3月18日
	 * @param beginDate   开始时间
	 * @param endDate      结束时间
	 * @param timeUnit     时间单位：1-年、2-月、3-日、4-小时、5-分钟、6-秒
	 * @return
	 */
	public static long compare(Date beginDate, Date endDate, int timeUnit) {
		long bTime = beginDate.getTime();
		long eTime = endDate.getTime();
		long time = eTime - bTime;
		switch(timeUnit) {
			case YEAR :
				time = time/1000/60/60/24/365;
				break;
			case MONTH :
				time = time/1000/60/60/24/30;
				break;
			case DAY :
				time = time/1000/60/60/24;
				break;
			case HOUR :
				time = time/1000/60/60;
				break;
			case MINUTE :
				time = time/1000/60;
				break;
			case SECOND :
				time = time/1000;
				break;
		}
		return time;
	}

}
