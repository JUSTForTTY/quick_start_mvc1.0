package com.tcj.common.util;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xuah
 * 
 *         2012-12-21-下午3:17:36 工具类
 */
public class DateUtil {
	
	private static Log log = LogFactory.getLog(DateUtil.class) ;
	
	/**
	 * @return 返回一个新的日期
	 */
	public static java.sql.Date newDate() {
		return new java.sql.Date(new Date().getTime());
	}
	
	/**
	 * @return 返回一个新的日期
	 */
	public static java.sql.Time  newTime(){
		return new java.sql.Time(new Date().getTime()) ;
	}
	
	/**
	 * @return 返回一个新的日期
	 */
	public static java.sql.Timestamp  newTimestamp(){
		return new java.sql.Timestamp(new Date().getTime()) ;
	}

	/**
	 * @param input
	 * @param format
	 * @return 根据指定格式，格式化字符串为日期类型
	 */
	public static Date parse(String input, String format) {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			date = df.parse(input);
		} catch (Exception e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.CHINA);
				return sdf.parse(input);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * @param date
	 * @param field
	 * @param amount
	 * @return 将日期参与计算
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar cal = date2Cal(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * @param input
	 * @return 以yyyy-MM-dd HH:mm:ss格式，格式化字符串为日期
	 */
	public static Date parse(String input) {
		return parse(input, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param date
	 * @return 格式化日期类为字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param input
	 * @param format
	 * @return 按一定的格式 格式化日期类型
	 */
	public static String format(Date input, String format) {
		if (input == null)
			return null;
		String date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			date = df.format(input);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return date;
	}

	/**
	 * @param input
	 * @param format
	 * @return 格式化Calendar为字符串
	 */
	public static String format(Calendar input, String format) {
		return format(input.getTime(), format);
	}

	/**
	 * @param input
	 * @return 格式化Calendar为字符串
	 */
	public static String format(Calendar input) {
		return format(input.getTime());
	}

	/**
	 * @return 返回一日历类型
	 */
	public static Calendar newCal() {
		return Calendar.getInstance(TimeZone.getTimeZone("ETC/GMT-8"));
	}

	/**
	 * @param calendar
	 * @return 将calendar 转为 date类型
	 */
	public static Date cal2Date(Calendar calendar) {
		if (calendar == null)
			return null;
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * @param date
	 * @return 将日期类型转为日历类型
	 */
	public static Calendar date2Cal(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("ETC/GMT-8"));
		calendar.setTime(date);
		return calendar;
	}

	public static int getDaysBetween(java.util.Calendar d1,
			java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
				- d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 计算2个日期之间的相隔天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getWorkingDay(java.util.Calendar d1, java.util.Calendar d2) {
		int result = -1;
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}

		int betweendays = getDaysBetween(d1, d2);

		int charge_date = 0;
		int charge_start_date = 0;// 开始日期的日期偏移量
		int charge_end_date = 0;// 结束日期的日期偏移量
		// 日期不在同一个日期内
		int stmp;
		int etmp;
		stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
			charge_start_date = stmp - 1;
		}
		if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
			charge_end_date = etmp - 1;
		}
		// }
		result = (getDaysBetween(getNextMonday(d1), getNextMonday(d2)) / 7) * 5
				+ charge_start_date - charge_end_date;
		return result;
	}

	public static String getChineseWeek(Date date) {
		return getChineseWeek(date2Cal(date));
	}

	public static String getChineseWeek(Calendar date) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };

		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

		// System.out.println(dayNames[dayOfWeek - 1]);
		return dayNames[dayOfWeek - 1];

	}

	/**
	 * 获得日期的下一个星期一的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(Calendar.DATE, 1);
		} while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}

	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getHolidays(Calendar d1, Calendar d2) {
		return getDaysBetween(d1, d2) - getWorkingDay(d1, d2);
	}
}
