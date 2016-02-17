package com.ibailian.android.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd",Locale.getDefault());
	private static SimpleDateFormat timeStampSdf = new SimpleDateFormat("yyyyMMddHHmmss",Locale.getDefault());
	public static final String SH_LIBRARY_START_TIME = "2007-01-01";

	public static String formatDate(String date) {
		String formatDate = date;
		try {
			formatDate = sdf.format(sdf.parse(date));
		} catch (ParseException e) {
		}
		return formatDate;
	}

	public static boolean befor(String date1, String date2) {
		if (StringUtils.isEmpty(date1)) {
			return false;
		}
		if (StringUtils.isEmpty(date2)) {
			return false;
		}
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			return d1.before(d2);
		} catch (ParseException e) {
		}
		return false;
	}

	public static boolean after(String date1, String date2) {
		if ("".equals(date1)) {
			return false;
		}
		if ("".equals(date2)) {
			return false;
		}
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			return d1.after(d2);
		} catch (ParseException e) {
		}
		return false;
	}

	public static boolean beforCurrentTime(String date) {
		try {
			Date d = sdf.parse(date);
			return d.before(new Date());
		} catch (ParseException e) {
		}
		return false;
	}

	public static String formatDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String formatDate = date;
		try {
			formatDate = sdf.format(sdf.parse(date));
		} catch (ParseException e) {
		}
		return formatDate;
	}

	public static String getCurrentTime() {
		String result = sdf.format(new Date());
		return result;
	}

	public static String getCurrentTime(String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String result = sdf.format(new Date());
			return result;
		} catch (Exception e) {
		}
		return "";
	}

	public static String addDay(String currentDate, int day) {
		try {
			Date date = sdf.parse(currentDate);
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(date);
			grc.add(GregorianCalendar.DAY_OF_MONTH, day);
			return sdf2.format(grc.getTime());
		} catch (Exception e) {
		}
		return "";
	}

	public static String getAfterCurrentTime(int month) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date());
			grc.add(GregorianCalendar.MONTH, month);
			return sdf.format(grc.getTime());
		} catch (Exception e) {
		}
		return "";
	}
	public static String getAfterCurrentTimeDay(int day) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date());
			grc.add(GregorianCalendar.DATE, day);
			return sdf.format(grc.getTime());
		} catch (Exception e) {
		}
		return "";
	}

	public static String getBeforeCurrentTime(String date, int month) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(sdf.parse(date));
			grc.add(GregorianCalendar.MONTH, -month);
			String reslt = sdf.format(grc.getTime());
			return reslt;
		} catch (Exception e) {
		}
		return "";
	}

	public static String getBeforeCurrentTimeDay(int day) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date());
			grc.add(GregorianCalendar.DATE, -day);
			String result = sdf.format(grc.getTime());
			return result;
		} catch (Exception e) {
		}
		return "";
	}

	public static String getBeforeCurrentTimeDay(int day, String format) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date());
			grc.add(GregorianCalendar.DATE, -day);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String result = sdf.format(grc.getTime());
			return result;
		} catch (Exception e) {
		}
		return "";
	}

	public static String getBeforeCurrentTimeDay(String date, int day) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(sdf.parse(date));
			grc.add(GregorianCalendar.DATE, -day);
			String result = sdf.format(grc.getTime());
			return result;
		} catch (Exception e) {
		}
		return "";
	}

	public static String getTimeStamp() {
		try {
			return timeStampSdf.format(new Date());
		} catch (Exception e) {
		}
		return "";
	}
	
	public static String getBeforeCurrentTimeWeek(String date, int day) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(sdf.parse(date));
			grc.add(GregorianCalendar.DATE, -day);
			String result = sdf.format(grc.getTime());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
		
	}
	public static String getBeforeCurrentTimeMonth(String date, int month) {
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(sdf.parse(date));
			grc.add(GregorianCalendar.MONTH, -month);
			String result = sdf.format(grc.getTime());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
		
	}

	  /**
     * 获得当前日期后longtime日期
     * @param longtime
     * @return
     */
    public static String getAfterDate(int longtime){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, longtime);

		return (new SimpleDateFormat("yyyyMMdd").format(cal.getTime()));
	}

}
