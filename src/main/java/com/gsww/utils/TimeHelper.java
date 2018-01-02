package com.gsww.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Wurd 2005-11-27
 * 
 *         日期帮助类
 */
public class TimeHelper {
	private static String CurrentTime;

	private static String CurrentDate;

	public static final int MILLISECONDS_PER_SECOND = 1000;
	

	/**
	 * 得到当前的年份 返回格式:yyyy
	 * 
	 * @return String
	 */
	public static String getCurrentYear() {
		java.util.Date NowDate = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(NowDate);
	}

	public static long sub(String aMask, String strBeginDate, String strEndDate) {
		long dateRange = 0;
		Date beginDate = strToDate(aMask, strBeginDate);
		Date endDate = strToDate(aMask, strEndDate);
		dateRange = endDate.getTime() - beginDate.getTime();
		return dateRange;
	}

	public static String convertToTime(Long timestamp){
		  SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	       String data = format.format(timestamp);
	       return data;
	}
	public static Date strToDate(String aMask, String strDate) {
		SimpleDateFormat format = new SimpleDateFormat(aMask);
		Date date = null;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int noabs_subToSecondyMd(String strBeginDate,
			String strEndDate) {
		String secNum = "";
		long dateRange = sub("yyyyMMddHHmmss", strBeginDate, strEndDate);
		secNum = "" + (dateRange / MILLISECONDS_PER_SECOND);
		return Integer.parseInt(secNum);
	}

	/**
	 * 得到当前的月份 返回格式:MM
	 * 
	 * @return String
	 */
	public static String getCurrentMonth() {
		java.util.Date NowDate = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的日期 返回格式:dd
	 * 
	 * @return String
	 */
	public static String getCurrentDay() {
		java.util.Date NowDate = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的时间，精确到毫秒,共19位 返回格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}

	/**
	 * 得到当前的时间，精确到毫秒,共19位 返回格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getCurrentTime1() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss SSS");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}


	public static Date getCurrentDateTime() {
		Date NowDate = new Date();
		return NowDate;
	}

	public static String getCurrentCompactTime() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}

	public static String getCT() {
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}

	public static String convetyyyyMMddHHmmss(Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String strdate = "";
		try {
			strdate = formatter.format(date);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strdate;
	}
	/**
	 * 得到当前的时间，精确到毫秒, 返回格式:yyyyMMddHHmmssSSS
	 * 
	 * @return String
	 */
	public static String getCurrentMillisecond() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}

	public static String getYesterdayCompactTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, -1);
		System.out.print(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}

	public static String getYesterdayCompactTimeForFileName() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, -1);
		System.out.print(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}

	/**
	 * 获取前一天日期
	 * @return
	 */
	public static String getYesterdayDate() {
		 Calendar cal=Calendar.getInstance();
		 cal.add(cal.DATE, -1);
		 SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		 CurrentTime=formatter.format(cal.getTime());
		 return CurrentTime;
		 
	}
	/**
	 * 获取前一月的年月
	 * @return
	 */
	
	public static String getLastMonth(){
		Calendar cal=Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM");
		CurrentTime=formatter.format(cal.getTime());
		return CurrentTime;
	}
	/**
	 * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	public static Date getCurrDate() {
		Date NowDate = new Date();
		return NowDate;
	}

	/**
	 * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentDate1() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	/**
	 * 得到当月的第一天,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentFirstDate() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	/**
	 * 得到当月的最后一天,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static String getCurrentLastDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = null;
		try {
			java.util.Date date = formatter.parse(getCurrentFirstDate());
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			return formatDate(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(java.util.Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(java.util.Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String formatDateStrByFormat(String date, String format) {
		String result = "";
		Date tmpdate = TimeHelper.parseDate(date);
		if (tmpdate != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(tmpdate);
			} catch (Exception ex) {
				result = date;
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 得到当前日期加上某一个整数的日期，整数代表天数 输入参数：currentdate : String 格式 yyyy-MM-dd add_day :
	 * int 返回格式：yyyy-MM-dd
	 */
	public static String addDay(String currentdate, int add_day) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.DATE, add_day);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getAddDay(String currentdate, int add_day) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));
			hour = Integer.parseInt(currentdate.substring(11, 13));
			minute = Integer.parseInt(currentdate.substring(14, 16));
			second = Integer.parseInt(currentdate.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.DATE, add_day);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到当前日期加上某一个整数的日期，整数代表月数 输入参数：currentdate : String 格式 yyyy-MM-dd add_month
	 * : int 返回格式：yyyy-MM-dd
	 */
	public static String addMonth(String currentdate, int add_month) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.MONTH, add_month);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到endTime比beforeTime晚几个月，整数代表月数 输入参数：endTime、beforeTime : String
	 * 格式前7位的格式为 yyyy-MM
	 */
	public static int monthDiff(String beforeTime, String endTime) {
		if (beforeTime == null || endTime == null) {
			return 0;
		}
		int beforeYear, endYear, beforeMonth, endMonth;
		try {
			beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
			endYear = Integer.parseInt(endTime.substring(0, 4));
			beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
			endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
			return (endYear - beforeYear) * 12 + (endMonth - beforeMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int dayDiff(String beforeTime, String endTime) {
		if (beforeTime == null || endTime == null) {
			return 0;
		}
		int beforeYear, endYear, beforeMonth, endMonth, beforeDay, endDay;
		try {
			beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
			endYear = Integer.parseInt(endTime.substring(0, 4));
			beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
			endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
			beforeDay = Integer.parseInt(beforeTime.substring(8, 10)) - 1;
			endDay = Integer.parseInt(endTime.substring(8, 10)) - 1;
			return (endYear - beforeYear) * 365 + (endMonth - beforeMonth) * 30
					+ (endDay - beforeDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到当前日期加上某一个整数的分钟 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
	 * add_minute : int 返回格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String addMinute(String currentdatetime, int add_minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(11, 13));
			minute = Integer.parseInt(currentdatetime.substring(14, 16));
			second = Integer.parseInt(currentdatetime.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.MINUTE, add_minute);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String addMonthformat(String currentdate, int add_month) {

		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.MONTH, add_month);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String addMonthformatNew(String currentdate, int add_month) {

		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.MONTH, add_month);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到当前日期加上某一个整数的秒 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
	 * add_second : int 返回格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String addSecond(String currentdatetime, int add_second) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(11, 13));
			minute = Integer.parseInt(currentdatetime.substring(14, 16));
			second = Integer.parseInt(currentdatetime.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.SECOND, add_second);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String addMinute1(String currentdatetime, int add_minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(8, 10));
			minute = Integer.parseInt(currentdatetime.substring(8, 10));
			second = Integer.parseInt(currentdatetime.substring(8, 10));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.MINUTE, add_minute);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(String sDate) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = bartDateFormat.parse(sDate);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	public static String parseDate(String sDate,String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);

		try {
			String date = bartDateFormat.format(sDate);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	/**
	 * 解析日期及时间
	 * 
	 * @param sDateTime
	 *            日期及时间字符串
	 * @return 日期
	 */
	public static Date parseDateTime(String sDateTime) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			Date date = bartDateFormat.parse(sDateTime);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * 取得一个月的天数 date:yyyy-MM-dd
	 * 
	 * @throws ParseException
	 */
	public static int getTotalDaysOfMonth(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();

		Date date = null;
		try {
			date = sdf.parse(strDate);
			calendar.setTime(date);
			int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 天数
			return day;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static long getDateSubDay(String startDate, String endDate) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long theday = 0;
		try {
			calendar.setTime(sdf.parse(startDate));
			long timethis = calendar.getTimeInMillis();
			calendar.setTime(sdf.parse(endDate));
			long timeend = calendar.getTimeInMillis();
			theday = (timethis - timeend) / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theday;
	}

	public static long getTimeDiff(String startDate, String endDate) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long theHour = 0;
		try {
			calendar.setTime(sdf.parse(startDate));
			long timethis = calendar.getTimeInMillis();
			calendar.setTime(sdf.parse(endDate));
			long timeend = calendar.getTimeInMillis();
			theHour = (timethis - timeend) / (1000 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theHour;
	}
	public static Date strToDate(String strDate) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  ParsePosition pos = new ParsePosition(0);
		  Date strtodate = formatter.parse(strDate, pos);
		  return strtodate;

		 }

		 
		 public static boolean isLeapYear(String ddate) {
		  
		  Date d = strToDate(ddate);
		  GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		  gc.setTime(d);
		  int year = gc.get(Calendar.YEAR);
		  if ((year % 400) == 0)
		   return true;
		  else if ((year % 4) == 0) {
		   if ((year % 100) == 0)
		    return false;
		   else
		    return true;
		  } else
		   return false;
		 }

		 //获取某一月的最后一天
		 public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		  String str = dat.substring(0, 8);
		  String month = dat.substring(5, 7);
		  int mon = Integer.parseInt(month);
		  if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
		    || mon == 10 || mon == 12) {
		   str += "31";
		  } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
		   str += "30";
		  } else {
		   if (isLeapYear(dat)) {
		    str += "29";
		   } else {
		    str += "28";
		   }
		  }
		  return str;
		 }

		
		 public static int getDayOfMonth(String month){
				Calendar rightNow = Calendar.getInstance();
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM"); //如果写成年月日的形式的话，要写小d，如："yyyy/MM/dd"
				try {
				rightNow.setTime(simpleDate.parse(month)); //要计算你想要的月份，改变这里即可
				} catch (ParseException e) {
				e.printStackTrace();
				}
				int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
				return days;
			}
		 public static String getUpMonth(){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -1);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
				return dateFormat.format(c.getTime());
			}

		 /**
			 * 得到当前的时间，精确到毫秒, 返回格式:yyyyMMddHHmmss
			 * 
			 * @return String
			 */
			public static String getCurrentSecond() {
				Date NowDate = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyyMMddHHmmss");
				CurrentTime = formatter.format(NowDate);
				return CurrentTime;
			}
			public static Date covertToDate(String dateString){
				Date date = null;
				try  
				{  
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
				    date = sdf.parse(dateString);  
				}  
				catch (ParseException e)  
				{  
				    System.out.println(e.getMessage());  
				}
				return date;
			}
			
			 public static boolean compare_time(String now, String time1, String time2) {
				 DateFormat df = new SimpleDateFormat("HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒
			        try {
			        	Date nowt = df.parse(now);
			            Date dt1 = df.parse(time1);
			            Date dt2 = df.parse(time2);
			            if (nowt.getTime() >= dt1.getTime()&&nowt.getTime() <= dt2.getTime()) {
			                return true;
			            } else {
			            	return false;
			            }
			        } catch (Exception exception) {
			            exception.printStackTrace();
			        }
					return false;
			       
			} 
	public static void main(String[] args) {
		// long d = 0;
		// String monthtimestr =
		// TimeHelper.getCurrentYear()+"-"+TimeHelper.getCurrentMonth()+"-01 00:00:00";
		// String yeartimestr =
		// Integer.toString((Integer.parseInt(TimeHelper.getCurrentYear())+1))+"-01-01 00:00:00";
		// System.out.println(TimeHelper.addMonthformatNew(monthtimestr,1));
		// System.out.println(monthtimestr);
		// System.out.println(yeartimestr);
		// System.out.println(TimeHelper.getCurrentYear()+"-01-01 00:00:00");

//		System.out.println(getTimeDiff(TimeHelper.getCurrentTime(),
//				"2012-6-14 17:50:05"));
//		System.out.println(TimeHelper.dayDiff("2012-01-01 00:00:00",
//				"2012-01-01 23:59:59"));
		System.out.println(compare_time("23:12:14","23:12:12","23:13:11"));
	}
}
