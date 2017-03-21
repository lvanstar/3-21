/**
 * 时间格式应用类
 * @title DateUtil.java
 * @package com.sinsoft.android.util
 * @author leong  
 */
package com.qiansong.msparis.app.commom.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * 日期操作工具类.
 * 
 */

public class DateUtil {

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 日期格式
	public static String TIME_FORMAT_M_D_H_M = "MM-dd HH:mm";
	private final static Long DAY_MILLISECOND = 86400000L;
	private final static Long MINUTE_MILLISECOND = 60000L;
	// 格式化 时、分
	public static String TIME_FORMAT_HM = "HH:mm";
	public static int DATE_FORMAT=2;
	public static int LOOKING_FORMAT=1;


	/**设置每个阶段时间*/
	private static final int seconds_of_1minute = 60;

	private static final int seconds_of_30minutes = 30 * 60;

	private static final int seconds_of_1hour = 60 * 60;

	private static final int seconds_of_1day = 24 * 60 * 60;

	private static final int seconds_of_15days = seconds_of_1day * 15;

	private static final int seconds_of_30days = seconds_of_1day * 30;

	private static final int seconds_of_6months = seconds_of_30days * 6;

	private static final int seconds_of_1year = seconds_of_30days * 12;

	/**
	 * <p>
	 * Checks if two dates are on the same day ignoring time.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * <p>
	 * Checks if two calendars represent the same day ignoring time.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * <p>
	 * Checks if a date is today.
	 * </p>
	 * 
	 * @param date
	 *            the date, not altered, not null.
	 * @return true if the date is today.
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 */
	public static boolean isToday(Date date) {
		return isSameDay(date, Calendar.getInstance().getTime());
	}

	/**
	 * <p>
	 * Checks if a calendar date is today.
	 * </p>
	 * 
	 * @param cal
	 *            the calendar, not altered, not null
	 * @return true if cal date is today
	 * @throws IllegalArgumentException
	 *             if the calendar is <code>null</code>
	 */
	public static boolean isToday(Calendar cal) {
		return isSameDay(cal, Calendar.getInstance());
	}

	/**
	 * <p>
	 * Checks if the first date is before the second date ignoring time.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if the first date day is before the second date day.
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 */
	public static boolean isBeforeDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isBeforeDay(cal1, cal2);
	}

	/**
	 * <p>
	 * Checks if the first calendar date is before the second calendar date
	 * ignoring time.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null.
	 * @param cal2
	 *            the second calendar, not altered, not null.
	 * @return true if cal1 date is before cal2 date ignoring time.
	 * @throws IllegalArgumentException
	 *             if either of the calendars are <code>null</code>
	 */
	public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA))
			return true;
		if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA))
			return false;
		if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR))
			return true;
		if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR))
			return false;
		return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * <p>
	 * Checks if the first date is after the second date ignoring time.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if the first date day is after the second date day.
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 */
	public static boolean isAfterDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isAfterDay(cal1, cal2);
	}

	/**
	 * <p>
	 * Checks if the first calendar date is after the second calendar date
	 * ignoring time.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null.
	 * @param cal2
	 *            the second calendar, not altered, not null.
	 * @return true if cal1 date is after cal2 date ignoring time.
	 * @throws IllegalArgumentException
	 *             if either of the calendars are <code>null</code>
	 */
	public static boolean isAfterDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA))
			return false;
		if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA))
			return true;
		if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR))
			return false;
		if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR))
			return true;
		return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * <p>
	 * Checks if a date is after today and within a number of days in the
	 * future.
	 * </p>
	 * 
	 * @param date
	 *            the date to check, not altered, not null.
	 * @param days
	 *            the number of days.
	 * @return true if the date day is after today and within days in the future
	 *         .
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 */
	public static boolean isWithinDaysFuture(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return isWithinDaysFuture(cal, days);
	}

	/**
	 * <p>
	 * Checks if a calendar date is after today and within a number of days in
	 * the future.
	 * </p>
	 * 
	 * @param cal
	 *            the calendar, not altered, not null
	 * @param days
	 *            the number of days.
	 * @return true if the calendar date day is after today and within days in
	 *         the future .
	 * @throws IllegalArgumentException
	 *             if the calendar is <code>null</code>
	 */
	public static boolean isWithinDaysFuture(Calendar cal, int days) {
		if (cal == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar today = Calendar.getInstance();
		Calendar future = Calendar.getInstance();
		future.add(Calendar.DAY_OF_YEAR, days);
		return (isAfterDay(cal, today) && !isAfterDay(cal, future));
	}

	/**
	 * <p>
	 * Checks if a calendar date is before today and within a number of days in
	 * the past.
	 * </p>
	 * 
	 * @param cal
	 *            the calendar, not altered, not null
	 * @param days
	 *            the number of days.
	 * @return true if the calendar date day is after today and within days in
	 *         the past .
	 * @throws IllegalArgumentException
	 *             if the calendar is <code>null</code>
	 */
	public static boolean isWithinDaysPast(Calendar cal, int days) {
		if (cal == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar today = Calendar.getInstance();
		Calendar past = Calendar.getInstance();

		past.add(Calendar.DAY_OF_YEAR, -1 * days);
		return (isBeforeDay(cal, today) && !isBeforeDay(cal, past));
	}

	/** Returns the given date with the time set to the start of the day. */
	public static Date getStart(Date date) {
		return clearTime(date);
	}

	/** Returns the given date with the time values cleared. */
	public static Date clearTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	/**
	 * Determines whether or not a date has any time values (hour, minute,
	 * seconds or millisecondsReturns the given date with the time values
	 * cleared.
	 */

	/**
	 * Determines whether or not a date has any time values.
	 * 
	 * @param date
	 *            The date.
	 * @return true iff the date is not null and any of the date's hour, minute,
	 *         seconds or millisecond values are greater than zero.
	 */
	public static boolean hasTime(Date date) {
		if (date == null) {
			return false;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (c.get(Calendar.HOUR_OF_DAY) > 0) {
			return true;
		}
		if (c.get(Calendar.MINUTE) > 0) {
			return true;
		}
		if (c.get(Calendar.SECOND) > 0) {
			return true;
		}
		if (c.get(Calendar.MILLISECOND) > 0) {
			return true;
		}
		return false;
	}

	/** Returns the given date with time set to the end of the day */
	public static Date getEnd(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * Returns the maximum of two dates. A null date is treated as being less
	 * than any non-null date.
	 */
	public static Date max(Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return null;
		if (d1 == null)
			return d2;
		if (d2 == null)
			return d1;
		return (d1.after(d2)) ? d1 : d2;
	}

	/**
	 * Returns the minimum of two dates. A null date is treated as being greater
	 * than any non-null date.
	 */
	public static Date min(Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return null;
		if (d1 == null)
			return d2;
		if (d2 == null)
			return d1;
		return (d1.before(d2)) ? d1 : d2;
	}

	/** The maximum date possible. */
	public static Date MAX_DATE = new Date(Long.MAX_VALUE);

	public static Date str2Date(String str) {
		return str2Date(str, null);
	}

	public static Date str2Date(String str, String format) {
		if (str == null || str.length() == 0) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;

	}

	// date1 > date2
	public static int getAgeByDate(Date date1, Date date2) {
		int age = Math.abs(date1.getYear() - date2.getYear());

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(2012, date1.getMonth(), date1.getDate());
		cal2.set(2012, date2.getMonth(), date2.getDate());
		if (cal1.before(cal2) && age > 0)
			age--;
		return age;
	}

	public static int getAge(int _year, int _month, int _day) {

		GregorianCalendar cal = new GregorianCalendar();
		int y, m, d, a;

		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH);
		d = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(_year, _month, _day);
		a = y - cal.get(Calendar.YEAR);
		if ((m < cal.get(Calendar.MONTH)) || ((m == cal.get(Calendar.MONTH)) && (d < cal.get(Calendar.DAY_OF_MONTH)))) {
			--a;
		}
		if (a < 0)
			throw new IllegalArgumentException("Age &lt; 0");
		return a;
	}

	public static Calendar str2Calendar(String str) {
		return str2Calendar(str, null);

	}

	public static Calendar str2Calendar(String str, String format) {

		Date date = str2Date(str, format);
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c;

	}

	public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
		return date2Str(c, null);
	}

	public static String date2Str(Calendar c, String format) {
		if (c == null) {
			return null;
		}
		return date2Str(c.getTime(), format);
	}

	public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
		return date2Str(d, null);
	}

	public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String s = sdf.format(d);
		return s;
	}

	public static String getCurDateStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND);
	}

	/**
	 * 格式到 天
	 * 
	 * @return
	 */
	public static String getDateStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 *
	 * 获取现在时间
	 */
	public static String NowString(int i){
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = sf.format(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sf.parse(nowDate));
			cal.add(Calendar.DAY_OF_YEAR, +i);
			String nextDate_1 = sf.format(cal.getTime());

			return nextDate_1;
		}catch (Exception e){
			return "";

		}
	}

	/**
	 * 获取今天时间  月-日
	 *
	 * @return
	 */
	public static String getDateOne(int i)  {
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
			String nowDate = sf.format(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sf.parse(nowDate));
			cal.add(Calendar.DAY_OF_YEAR, +i);
			String nextDate_1 = sf.format(cal.getTime());
			return nextDate_1;
		}catch (Exception e){
			return "";

		}
	}

	/**
	 * 显示现在时间
	 * 
	 * @return
	 */
	public static String getTimeStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.HOUR_OF_DAY) + "：" + (c.get(Calendar.MINUTE));
	}

	/**
	 * 获得当前日期的字符串格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDateStr(String format) {
		Calendar c = Calendar.getInstance();
		return date2Str(c, format);
	}


	/**
	 * 时间戳转date类型
	 */

	public static Date getCurForDate(long time){
		Date date=null;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(time*1000);
		ContentUtil.makeLog("lzz","d:"+d);
		try {
			date=format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将字符串转换成时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrToDate(String time) {
		Date date;
		String newDate = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			newDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;

	}

	/**
	 * 将字符串yyyy年MM月dd日转换成yyyy-MM-dd时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrDate(String time) {
		Date date;
		String newDate = null;
		try {
			date = new SimpleDateFormat("yyyy年MM月dd日").parse(time);
			newDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;

	}

	/**
	 * 将字符串yyyy年MM月dd日转换成yyyy时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrDateYear(String time) {
		Date date;
		String newDate = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			newDate = new SimpleDateFormat("yyyy").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;

	}

	/**
	 * 将字符串yyyy年MM月dd日转换成MM时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrDateMonth(String time) {
		Date date;
		String newDate = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			newDate = new SimpleDateFormat("MM").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;

	}
	/***
	 * date转String
	 * 
	 */
	public static String getStringTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	

	/**
	 * 将字符串yyyy年MM月dd日转换成Day时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrDateDay(String time) {
		Date date;
		String newDate = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			newDate = new SimpleDateFormat("dd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;

	}

	// 格式到分
	public static String getMinute(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(time);

	}

	// 格式到秒
	public static String getMillon(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

	}

	// 格式到天
	public static String getToDay(Date time) {

		return new SimpleDateFormat("yyyy年MM月dd日").format(time);

	}

	// 格式到天
	public static String getDay(long time) {

		return new SimpleDateFormat("MM月dd日").format(time);

	}

	// 格式到月
	public static String getMonth(long time) {

		return new SimpleDateFormat("yyyy年MM月").format(time);

	}

	// 格式时间
	public static String getTime(long time) {

		return new SimpleDateFormat("HH : mm").format(time);

	}

	// 格式时
	public static String getHour(long time) {

		return new SimpleDateFormat("HH").format(time);

	}

	// 格式分
	public static String getMinuter(long time) {

		return new SimpleDateFormat("mm").format(time);

	}

	// 格式到毫秒
	public static String getSMillon(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

	}

	// 格式到毫秒
	public static String getDate(long time) {

		return new SimpleDateFormat("yyyy-MM-dd").format(time*1000);

	}

	// 格式到毫秒
	public static String getSMillon2(Date time) {

		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(time);

	}

	public static long getDiffDaysByCalendar(Calendar calendar1, Calendar calendar2) {
		// 得到从 1970年01月01日到 calendar1 所经过的天数
		long time1 = calendar1.getTime().getTime() / DAY_MILLISECOND;
		// 得到从 1970年01月01日到 calendar2 所经过的天数
		long time2 = calendar2.getTime().getTime() / DAY_MILLISECOND;
		// 将两个经过的时间减出来就得到了它们之间的时间差
		return Math.abs(time1 - time2);
	}

	public static long getDiffDaysByDate(Date date1, Date date2) {

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);

		return getDiffDaysByCalendar(calendar1, calendar2);
	}

	public static long getDiffMinsByCalendar(Calendar calendar1, Calendar calendar2) {
		// 得到从 1970年01月01日到 calendar1 所经过的天数
		long time1 = calendar1.getTime().getTime() / MINUTE_MILLISECOND;
		// 得到从 1970年01月01日到 calendar2 所经过的天数
		long time2 = calendar2.getTime().getTime() / MINUTE_MILLISECOND;
		// 将两个经过的时间减出来就得到了它们之间的时间差
		return Math.abs(time1 - time2);
	}

	public static long getDiffMinsByDate(Date date1, Date date2) {

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);

		return getDiffMinsByCalendar(calendar1, calendar2);
	}

	/** */
	/**
	 * 取得日期是一年的第几周
	 * 
	 * @param
	 * @return
	 */
	public static int getWeekDayOfYear(Calendar calendar) {
		if (calendar == null)
			calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取两个日期之间的间隔天数
	 * 
	 * @return
	 */
	public static int getGapCount(Date startDate, Date endDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()));
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + "年第" + week + "周";
	}

	/**
	 * 取得今天是本月第几周
	 * 
	 * @return
	 */
	public static String getWeekAndDay() {
		String r = "";
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		// 获取当前时间为本月的第几周
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		// 获取当前时间为本周的第几天
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 1) {
			day = 7;
			week = week - 1;
		} else {
			day = day - 1;
		}
		r = year + "年" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "月第" + week + "周";
		return r;

	}

	/**
	 * 取得今天是本月第几周
	 * 
	 * @return
	 */
	public static String getMonth() {
		String r = "";
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		r = year + "年" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "月";
		return r;

	}

	/** */
	/**
	 * 取得日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstWeekDay(Date date) {
		initCalendar(date);
		gc.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return gc.getTime();
	}

	public static long getDays(long time){
		long days=0;
		long date=86400;
		days=time/date;
		return days;
	}

	/**
	 * 人性化显示时间，如：几小时前
	 * 
	 * @param ctime
	 * @return
	 */
	public static String showTime(long ctime) {
		String r = "";

		long nowtimelong = System.currentTimeMillis();
		long result = Math.abs(nowtimelong - ctime);

		if (result < 60000)// 一分钟内
		{
			long seconds = result / 1000;
			r = "刚刚";
		} else if (result >= 60000 && result < 3600000)// 一小时内
		{
			long seconds = result / 60000;
			r = seconds + "分钟前";
		} else if (result >= 3600000 && result < 86400000)// 一天内
		{
			long seconds = result / 3600000;
			r = seconds + "小时前";
		} else
		// 日期格式
		{
			SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT_M_D_H_M);
			Date date = new Date(ctime);
			r = sf.format(date);
		}
		return r;
	}

	/** */
	/**
	 * 取得日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastWeekDay(Date date) {
		initCalendar(date);
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstMonthDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		gc.add(Calendar.DAY_OF_MONTH, 1 - dayOfMonth);
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		int maxDaysOfMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		gc.add(Calendar.DAY_OF_MONTH, maxDaysOfMonth - dayOfMonth);
		return gc.getTime();
	}

	
	/**
	  * 得到某年某月的第一天
	  * 
	  * @param year
	  * @param month
	  * @return
	  */
	public static String getFirstDayOfMonth(int year, int month) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);

		cal.set(Calendar.MONTH, month - 1);

		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	  
	 /**
	  * 得到某年某月的最后一天
	  * 
	  * @param year
	  * @param month
	  * @return
	  */
	public static String getLastDayOfMonth(int year, int month) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);

		cal.set(Calendar.MONTH, month - 1);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}
	
	
	/** */
	/**
	 * 取得日期所在旬的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstTenDaysDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth <= 10) {
			gc.set(Calendar.DAY_OF_MONTH, 1);
		} else if (dayOfMonth > 20) {
			gc.set(Calendar.DAY_OF_MONTH, 21);
		} else {
			gc.set(Calendar.DAY_OF_MONTH, 11);
		}
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在旬的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastTenDaysDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth <= 10) {
			gc.set(Calendar.DAY_OF_MONTH, 10);
		} else if (dayOfMonth > 20) {
			gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			gc.set(Calendar.DAY_OF_MONTH, 19);
		}
		return gc.getTime();
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	private static void initCalendar(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("argument date must be not null");
		}
		gc.clear();
		gc.setTime(date);
	}

	private static GregorianCalendar gc = null;
	static {
		gc = new GregorianCalendar(Locale.CHINA);
		gc.setLenient(true);
		gc.setFirstDayOfWeek(Calendar.MONDAY);
	}

	/**
	 * @return 后天的日期
	 */
	public static Calendar getLastDay() {
		Calendar calendar = Calendar.getInstance();
		//
		calendar.get(Calendar.YEAR);
		calendar.roll(Calendar.DAY_OF_YEAR, -2);

		return calendar;

	}

	/**
	 * 获取今天的日期
	 * @return
     */
	public static String getToDay(int type){
		Calendar calendar=Calendar.getInstance();
		if(type==1){
			return
					calendar.get(GregorianCalendar.MONTH)+1+"月"+
							calendar.get(GregorianCalendar.DAY_OF_MONTH)+"日";
		}else {
			return
					calendar.get(GregorianCalendar.YEAR)+"-"+calendar.get(GregorianCalendar.MONTH)+1+"-"+
							calendar.get(GregorianCalendar.DAY_OF_MONTH);
		}
	}

	/**
	 * 获取后天的日期
	 * @return
     */
	public static String getTommorDay(int type){
		Calendar calendar=Calendar.getInstance();
		calendar.get(Calendar.YEAR);
		calendar.roll(Calendar.DAY_OF_YEAR, +1);

		if(type==1){
			return
					calendar.get(GregorianCalendar.MONTH)+1+"月"+
							calendar.get(GregorianCalendar.DAY_OF_MONTH)+"日";
		}else {
			return
					calendar.get(GregorianCalendar.YEAR)+"-"+calendar.get(GregorianCalendar.MONTH)+1+"-"+
							calendar.get(GregorianCalendar.DAY_OF_MONTH);
		}

	}

//根据时间计算时间
//	public static String getWeek(String pTime) {
//		String Week = "";
//		if (pTime != null && pTime.length() > 0) {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			Calendar c = Calendar.getInstance();
//			try {
//				c.setTime(format.parse(pTime));
//
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 1) {
//				Week += "日";
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 2) {
//				Week += "一";
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 3) {
//				Week += "二";
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 4) {
//				Week += "三";
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 5) {
//				Week += "四";
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 6) {
//				Week += "五";
//			}
//			if (c.get(Calendar.DAY_OF_WEEK) == 7) {
//				Week += "六";
//			}
//		}
//		return Week;
//	}
	
	/**
	 * 获取现在时间  判断是否为传输的2个时间段中间
	 * 
	 * @param strDateBegin
	 * @param strDateEnd
	 * @return
	 */
	public static boolean isInDate(String strDateBegin, String strDateEnd) {
		long start = 0;
		long end = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(strDateBegin);
			start = date.getTime();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Date date2;
		try {
			date2 = sdf.parse(strDateEnd);
			end = date2.getTime();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Date date3 = new Date();
		long newTime = date3.getTime();

		if (newTime > start && newTime < end) {
			return true;
		} else {
			return false;
		}
	}
	
	/***
	 * 获取现在时间为long类型
	 * 
	 */
	public  static long getNewDateByLong(){
		long newTime = 0;
		Date date = new Date();
		newTime = date.getTime();
		return newTime;
	}
	/**
	 * 
	 * 判断现在时间是否大于保存时间差
	 * 
	 */
	public  static boolean getOldeDateByNewTime(long startTime,float interval){
		long newTime = 0;
		Date date = new Date();
		newTime = date.getTime();
		if((newTime - startTime)/1000 > interval){
			return true;
		}
		return false;
	}





	/**
	 * 格式化时间
	 * @param mTime
	 * @return
	 */
	public static String getTimeRange(long mTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/**获取当前时间*/
		long  curDate = System.currentTimeMillis();

		/**除以1000是为了转换成秒*/
		long   between=(curDate-mTime*1000);
		int   elapsedTime= (int) (between);
		if (elapsedTime < seconds_of_1minute) {

			return "刚刚";
		}
		if (elapsedTime < seconds_of_30minutes) {

			return elapsedTime / seconds_of_1minute + "分钟前";
		}
		if (elapsedTime < seconds_of_1hour) {

			return "半小时前";
		}
		if (elapsedTime < seconds_of_1day) {

			return elapsedTime / seconds_of_1hour + "小时前";
		}
		if (elapsedTime < seconds_of_15days) {

			return elapsedTime / seconds_of_1day + "天前";
		}
		if (elapsedTime < seconds_of_30days) {

			return "半个月前";
		}
		if (elapsedTime < seconds_of_6months) {

			return elapsedTime / seconds_of_30days + "月前";
		}
		if (elapsedTime < seconds_of_1year) {

			return "半年前";
		}
		if (elapsedTime >= seconds_of_1year) {

			return elapsedTime / seconds_of_1year + "年前";
		}
		return "";

}


	public static int toTimeLong(int timeDay){

		int timeStamp = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try{
			String value=String.valueOf(timeDay);
			d = sdf.parse(value.substring(0,4)+"-"+value.substring(4,6)+"-"+value.substring(6,8));
			timeStamp = (int)(d.getTime()/1000);
		} catch(ParseException e){
			e.printStackTrace();
		}
		return timeStamp;
	}

	public static int [] toTimeLongOr(int []timeDay){

		int timeStamp[] = new int[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try{
			String value01=String.valueOf(timeDay[0]);
			String value02=String.valueOf(timeDay[1]);
			d = sdf.parse(value01.substring(0,4)+"-"+value01.substring(4,6)+"-"+value01.substring(6,8));
			int time01 = (int)(d.getTime()/1000);
			d = sdf.parse(value02.substring(0,4)+"-"+value02.substring(4,6)+"-"+value02.substring(6,8));
			int time02 = (int)(d.getTime()/1000);
			timeStamp=new int[]{time01,time02};
		} catch(ParseException e){
			e.printStackTrace();
		}
		return timeStamp;
	}

}


