package com.ibailian.android.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

public class StringUtils {

	private static final String TAG = "StringUtils";

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static String filterNull(String input) {
		if (StringUtils.isEmpty(input)) {
			return "";
		}
		return input;
	}

	public static String filterEmptyWord(String input) {
		if (StringUtils.isEmpty(input)) {
			return "";
		}
		return input.replace(" ", "");
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	public static Double toDouble(String str, Double defValue) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
		}
		
		return defValue;
	}
	
	public static String getTwoDecimalPointDouble_String(double value) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		return df.format(value).toString();
	}

	public static Double getTwoDecimalPointDouble(double value) {
		
		Log.i(TAG, "value:"+value);
		DecimalFormat df = new DecimalFormat("0.00");
		Log.i(TAG, "df.format(value).toString():"+df.format(value).toString());
		return toDouble(df.format(value).toString(), 0.00);
	}

	public static Double getOneDecimalPointDouble(double value) {
		DecimalFormat df = new DecimalFormat("0.0");
		return toDouble(df.format(value).toString(), 0.0);
	}
	public static Double getThreeDecimalPointDouble(double value) {
		DecimalFormat df = new DecimalFormat("0.000");
		return toDouble(df.format(value).toString(), 0.000);
	}

	public static Boolean toBoolean(String str, boolean defValue) {
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	public static float toFloat(String str, int defValue) {
		try {
			return Float.parseFloat(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	public static byte[] readBytes(InputStream is) {
		try {
			ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				byteBuffer.write(buffer, 0, len);
			}
			return byteBuffer.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	public static String getLast4Word(String spliteStr) {
		if (StringUtils.isEmpty(spliteStr)) {
			return "";
		}
		if (spliteStr.length() > 4) {
			return spliteStr.substring(spliteStr.length() - 4, spliteStr.length());
		}
		return spliteStr;
	}
	
	public static String getLast8Word(String spliteStr) {
		if (StringUtils.isEmpty(spliteStr)) {
			return "";
		}
		if (spliteStr.length() > 8) {
			return spliteStr.substring(spliteStr.length() - 8, spliteStr.length());
		}
		return spliteStr;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param strMobile
	 * @return 是：true；不是：false
	 */
	public static boolean isMobile(String strMobile) {
		if (!isEmpty(strMobile) && strMobile.length() == 11) {
			try {
				Long mobile = Long.parseLong(strMobile);
				String estr = String.valueOf(mobile).substring(0, 2);
				if (estr.equals("13") || estr.equals("14") || estr.equals("15") || estr.equals("18")) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 验证手机号码是否正确
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNumber(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		boolean match = m.matches();
		if (!match) {
			if (mobiles.indexOf("00") == 0 || mobiles.indexOf("+86") == 0) {
				match = true;
			}
		}
		return match;
	}

	// storage, G M K B
	public static String convertStorage(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
	}

	public static String getString16(String str16, int charLength) {
		if (charLength > str16.length()) {
			String s = "";
			for (int i = 0; i < charLength - str16.length(); i++) {
				s += "0";
			}
			str16 = s + str16;
		}
		return str16;
	}

	public static String[] getNum(double value) {
		String[] result = new String[] { "0", "0" };
		String dyeStr = String.valueOf(value);

		String[] numArray = dyeStr.split("\\.");
		String num = "";

		if (numArray.length > 0) {
			num = numArray[1];
		}

		if (num.length() == 1) {
			result[0] = num.substring(0, 1);
		} else if (num.length() > 1) {
			result[0] = num.substring(0, 1);
			result[1] = num.substring(1, 2);
		}
		return result;
	}

	public static String parseValueByKey(String url, String key) {
		try {
			String value = "";
			URL u = new URL(url);
			String s = u.getQuery();
			if (s != null) {
				String array[] = s.split("&");
				for (String parameter : array) {
					String v[] = parameter.split("=");
					try {
						String keyValue = URLDecoder.decode(v[0], "UTF-8");
						if (key.equals(keyValue)) {
							value = URLDecoder.decode(v[1], "UTF-8");
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			return value;
		} catch (MalformedURLException e) {
			return "";
		}
	}
	
	/**
	 * 变化字符串中数字的大小，小数点前后大小不同
	 * @param price
	 * @param firstSize 小数点前数字大小
	 * @param secondSize小数点后数字大小
	 * @return
	 */
	public static CharSequence spanPrice(String price,int firstSize,int secondSize){
		
		Log.i(TAG, "price:"+price);
		
		if(StringUtils.isEmpty(price)){
			price="0.00";
		}
		double money=Double.parseDouble(price);
		DecimalFormat df = new DecimalFormat("0.00");
		price=df.format(money);
		
		Log.i(TAG, "price:"+price);
		
		int start = price.indexOf(".");
		int end = price.length();
		Spannable newPrice = new SpannableString(price);
		newPrice.setSpan(new AbsoluteSizeSpan(firstSize), 0, start, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		newPrice.setSpan(new AbsoluteSizeSpan(secondSize), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		
		Log.i(TAG, "price:"+price);
		
		return newPrice;
	}
	
	/**
	 * 将字符串中字符去掉，只保留数字
	 * @param str
	 * @return
	 */
	public static String isNumeric(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0;i<str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
	/**
	 * 根据传入的字变换大小
	 * @param text
	 * @return
	 */
	public static SpannableString spanTextStyle(String text,float firstSize,float secondSize) {
		
		
		//TODO
		if(StringUtils.isEmpty(text)){
			text="0.00";
		}
		double money=Double.parseDouble(text);
		DecimalFormat df = new DecimalFormat("0.00");
		text=df.format(money);
		
		SpannableString mspTest = new SpannableString(text);
		int dot = text.indexOf('.');
		if (dot > 0 && dot < text.length()) {
			mspTest.setSpan(new RelativeSizeSpan(firstSize), 0, dot, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 0.5f表示默认字体大小的一半
			mspTest.setSpan(new RelativeSizeSpan(secondSize), dot, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			mspTest.setSpan(new RelativeSizeSpan(firstSize), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return mspTest;
	}
	
	/**
	 * 数字每4位加一个空格
	 * @param number
	 * @return
	 */
	public static String numberAddOneSpace(String number){
		String []num = null;
		StringBuffer sb = new StringBuffer();
		num=number.split("");
		for(int i = 0;i<num.length;i++){
			Log.i(TAG, num[i]);
			if(i%4 ==0&&i>0){
				sb.append(num[i]);
				sb.append(" ");
			}else {
				sb.append(num[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将扫描到的字符变成******
	 * @param number
	 * @return
	 */
	public static String numberPassword(String number){
		StringBuffer sb = new StringBuffer();
		String []num = number.split("");
		for(int i =0;i<num.length;i++){
			sb.append("＊");
		}
		return StringUtils.numberAddOneSpace(sb.toString());
	}
	
	/**
	 * 比较date1是否早于date2
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1,String date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			if(d1.before(d2)){
				return 1;
			}else {
				return -1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			Log.i(TAG, "compareDateWrong!");
		}
		return 0;
	}
	/**
	 * 比较date1是否早于date2
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate2(String date1,String date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			if(d1.before(d2)){
				return true;
			}else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			Log.i(TAG, "compareDateWrong!");
		}
		return false;
	}
	/**
	 * a是b的百分之几
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getPercent(double a, double b) {
		return (int) (((double) a / (double) b) * 100);
	}
	public static String getVaitltime(String date){
		String[] time =date.split(" ");
		return time[0];
	}
	
	
	/**
	 * 格式化手机号码 xxx xxxx xxxx
	 * @param mobile
	 * @return
	 */
	public static String mobileFormat(String mobile){
		
		String s1 = mobile.substring(0,3);
		String s2 = mobile.substring(3,7);
		String s3 = mobile.substring(7,11);
		
		Log.i("123", s1+":"+s2+":"+s3);
		
		return s1+" "+s2+" "+s3;
	}
	
	
	
	
}
