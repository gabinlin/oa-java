/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package top.gabin.oa.web.utils.date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Utility class for handling java.util.Date, the java.sql data/time classes and
 * related
 *
 *  日期 工具类
 *
 */
public class UtilDateTime {
    private static final Logger logger = LoggerFactory.getLogger(UtilDateTime.class);

    public static final String[] months = {// // to be translated over
            // CommonMonthName, see example in
            // accounting
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December" };

    public static final String[] days = {// to be translated over CommonDayName,
                                         // see example in accounting
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    public static final String[][] timevals = { { "1000", "millisecond" }, { "60", "second" }, { "60", "minute" },
            { "24", "hour" }, { "168", "week" } };

    public static final DecimalFormat df = new DecimalFormat("0.00;-0.00");
    /**
     * JDBC escape format for java.sql.Date conversions.
     */
    //public static final String DATE_FORMAT = ;
    /**
     * JDBC escape format for java.sql.Timestamp conversions.
     */
    //public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * JDBC escape format for java.sql.Time conversions.
     */
    //public static final String TIME_FORMAT = "HH:mm:ss";
    
    public static final Set<String> PARSE_PATTERNS = new HashSet<String>();
    static{
        PARSE_PATTERNS.add("yyyy-MM-dd");
        PARSE_PATTERNS.add("yyyy-MM-dd HH:mm:ss.SSS");
        PARSE_PATTERNS.add("yyyy-MM-dd HH:mm:ss");
        PARSE_PATTERNS.add("yyyyMMddHHmmss");
    }

    /**
     *  获取两个 时间 的间隔
     * @param from
     * @param thru
     * @return
     */
    public static double getInterval(Date from, Date thru) {
        return thru != null ? thru.getTime() - from.getTime() : 0;
    }

    /**
     *  获取两个 时间 的间隔(天)
     *
     * @param from
     * @param thru
     * @return
     */
    public static int getIntervalInDays(Timestamp from, Timestamp thru) {
        return thru != null ? (int) ((thru.getTime() - from.getTime()) / (24 * 60 * 60 * 1000)) : 0;
    }

    /**
     *  获取两个 时间 的间隔(秒)
     *
     * @param from
     * @param thru
     * @return
     */
    public static int getIntervalInSeconds(Date from, Date thru) {
        return thru != null ? (int) ((thru.getTime() - from.getTime()) / (1000)) : 0;
    }

    public static Timestamp addDaysToTimestamp(Timestamp start, int days) {
        return new Timestamp(start.getTime() + (24L * 60L * 60L * 1000L * days));
    }

    public static Timestamp addDaysToTimestamp(Timestamp start, Double days) {
        return new Timestamp(start.getTime() + ((int) (24L * 60L * 60L * 1000L * days)));
    }

    /**
     *  获取两个 Timestamp 的间隔
     *
     * @param from
     * @param thru
     * @return
     */
    public static double getInterval(Timestamp from, Timestamp thru) {
        return thru != null ? thru.getTime() - from.getTime() + (thru.getNanos() - from.getNanos()) / 1000000 : 0;
    }

    /**
     * Return a Timestamp for right now
     * 
     * @return Timestamp for right now
     */
    public static Timestamp nowTimestamp() {
        return getTimestamp(System.currentTimeMillis());
    }

    /**
     * Convert a millisecond value to a Timestamp.
     *
     * @param time millsecond value
     * @return Timestamp
     */
    public static Timestamp getTimestamp(long time) {
        return new Timestamp(time);
    }

    /**
     * Convert a millisecond value to a Timestamp.
     *
     * @param milliSecs millsecond value
     * @return Timestamp
     */
    public static Timestamp getTimestamp(String milliSecs) throws NumberFormatException {
        return new Timestamp(Long.parseLong(milliSecs));
    }

    /**
     * Returns currentTimeMillis as String
     *
     * @return String(currentTimeMillis)
     */
    public static String nowAsString() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * Return a string formatted as yyyyMMddHHmmss
     *
     * @return String formatted for right now
     */
    public static String nowDateString() {
        return nowDateString("yyyyMMddHHmmss");
    }

    /**
     * Return a string formatted as format
     *
     * @return String formatted for right now
     */
    public static String nowDateString(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     *  格式化 date 为 字符串
     *
     * @param date
     * @return
     */
    public static String formatDate2String(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     *  格式化 data 为 字符串
     *
     * @param date
     * @return
     */
    public static String formatDate2ChinaString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return df.format(date);
    }

    /**
     *  格式化 data 为 字符串
     *
     * @param date
     * @param format    格式
     * @return
     */
    public static String formatDate2String(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * Return a Date for right now
     *
     * @return Date for right now
     */
    public static Date nowDate() {
        return new Date();
    }

    /**
     *  获取 一天的开始时间
     *
     * @param stamp
     * @return
     */
    public static Timestamp getDayStart(Timestamp stamp) {
        return getDayStart(stamp, 0);
    }

    /**
     *  获取 一天的开始时间
     *
     * @param stamp
     * @param daysLater
     * @return
     */
    public static Timestamp getDayStart(Timestamp stamp, int daysLater) {
        return getDayStart(stamp, daysLater, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     *  获取 下一天的 开始时间
     *
     * @param stamp
     * @return
     */
    public static Timestamp getNextDayStart(Timestamp stamp) {
        return getDayStart(stamp, 1);
    }

    /**
     *  获取 一天的 结束时间
     *
     * @param stamp
     * @return
     */
    public static Timestamp getDayEnd(Timestamp stamp) {
        return getDayEnd(stamp, Long.valueOf(0));
    }

    /**
     *  获取 一天的 结束时间
     *
     * @param stamp
     * @param daysLater
     * @return
     */
    public static Timestamp getDayEnd(Timestamp stamp, Long daysLater) {
        return getDayEnd(stamp, daysLater, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * Return the date for the first day of the year
     *
     * @param stamp
     * @return java.sql.Timestamp
     */
    public static Timestamp getYearStart(Timestamp stamp) {
        return getYearStart(stamp, 0, 0, 0);
    }

    /**
     *  获取 年的 第一天
     *
     * @param stamp
     * @param daysLater
     * @return
     */
    public static Timestamp getYearStart(Timestamp stamp, int daysLater) {
        return getYearStart(stamp, daysLater, 0, 0);
    }

    /**
     * 获取 年的 第一天
     *
     * @param stamp
     * @param daysLater
     * @param yearsLater
     * @return
     */
    public static Timestamp getYearStart(Timestamp stamp, int daysLater, int yearsLater) {
        return getYearStart(stamp, daysLater, 0, yearsLater);
    }

    /**
     * 获取 年的 第一天
     *
     * @param stamp
     * @param daysLater
     * @param monthsLater
     * @param yearsLater
     * @return
     */
    public static Timestamp getYearStart(Timestamp stamp, int daysLater, int monthsLater,
            int yearsLater) {
        return getYearStart(stamp, daysLater, monthsLater, yearsLater, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * 获取 年的 第一天
     *
     * @param stamp
     * @param daysLater
     * @param monthsLater
     * @param yearsLater
     * @return
     */
    public static Timestamp getYearStart(Timestamp stamp, Number daysLater, Number monthsLater,
            Number yearsLater) {
        return getYearStart(stamp, (daysLater == null ? 0 : daysLater.intValue()), (monthsLater == null ? 0
                : monthsLater.intValue()), (yearsLater == null ? 0 : yearsLater.intValue()));
    }

    /**
     * Return the date for the first day of the month
     *
     * @param stamp
     * @return java.sql.Timestamp
     */
    public static Timestamp getMonthStart(Timestamp stamp) {
        return getMonthStart(stamp, 0, 0);
    }

    public static Timestamp getMonthStart(Timestamp stamp, int daysLater) {
        return getMonthStart(stamp, daysLater, 0);
    }

    public static Timestamp getMonthStart(Timestamp stamp, int daysLater, int monthsLater) {
        return getMonthStart(stamp, daysLater, monthsLater, TimeZone.getDefault(), Locale.getDefault());
    }
    public static Timestamp getMonthEnd(Timestamp stamp) {
        return getMonthEnd(stamp, TimeZone.getDefault(), Locale.getDefault());
    }


    /*
    * 得到指定某年某月的最后一天
    */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
    }

    /*
    * 得到指定某年某月的第一天
    */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return   new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * Return the date for the first day of the week
     *
     * @param stamp
     * @return java.sql.Timestamp
     */
    public static Timestamp getWeekStart(Timestamp stamp) {
        return getWeekStart(stamp, 0, 0);
    }

    /**
     *  获取 周的 第一天
     *
     * @param stamp
     * @param daysLater
     * @return
     */
    public static Timestamp getWeekStart(Timestamp stamp, int daysLater) {
        return getWeekStart(stamp, daysLater, 0);
    }

    /**
     *  获取 周的 第一天
     *
     * @param stamp
     * @param daysLater
     * @param weeksLater
     * @return
     */
    public static Timestamp getWeekStart(Timestamp stamp, int daysLater, int weeksLater) {
        return getWeekStart(stamp, daysLater, weeksLater, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     *  获取 周的 最后一天
     *
     * @param stamp
     * @return
     */
    public static Timestamp getWeekEnd(Timestamp stamp) {
        return getWeekEnd(stamp, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     *  Timestamp 转化为 Calendar
     *
     * @param stamp
     * @return
     */
    public static Calendar toCalendar(Timestamp stamp) {
        Calendar cal = Calendar.getInstance();
        if (stamp != null) {
            cal.setTimeInMillis(stamp.getTime());
        }
        return cal;
    }

    /**
     * Converts a date String into a java.sql.Date
     *
     * @param date The date String: MM/DD/YYYY
     * @return A java.sql.Date made from the date String
     */
    public static java.sql.Date toSqlDate(String date) {
        Date newDate = toDate(date, "00:00:00");

        if (newDate != null) {
            return new java.sql.Date(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Makes a java.sql.Date from separate Strings for month, day, year
     *
     * @param monthStr The month String
     * @param dayStr The day String
     * @param yearStr The year String
     * @return A java.sql.Date made from separate Strings for month, day, year
     */
    public static java.sql.Date toSqlDate(String monthStr, String dayStr, String yearStr) {
        Date newDate = toDate(monthStr, dayStr, yearStr, "0", "0", "0");

        if (newDate != null) {
            return new java.sql.Date(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Makes a java.sql.Date from separate ints for month, day, year
     *
     * @param month The month int
     * @param day The day int
     * @param year The year int
     * @return A java.sql.Date made from separate ints for month, day, year
     */
    public static java.sql.Date toSqlDate(int month, int day, int year) {
        Date newDate = toDate(month, day, year, 0, 0, 0);

        if (newDate != null) {
            return new java.sql.Date(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Converts a time String into a java.sql.Time
     *
     * @param time The time String: either HH:MM or HH:MM:SS
     * @return A java.sql.Time made from the time String
     */
    public static java.sql.Time toSqlTime(String time) {
        Date newDate = toDate("1/1/1970", time);

        if (newDate != null) {
            return new java.sql.Time(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Makes a java.sql.Time from separate Strings for hour, minute, and second.
     *
     * @param hourStr The hour String
     * @param minuteStr The minute String
     * @param secondStr The second String
     * @return A java.sql.Time made from separate Strings for hour, minute, and
     *         second.
     */
    public static java.sql.Time toSqlTime(String hourStr, String minuteStr, String secondStr) {
        Date newDate = toDate("0", "0", "0", hourStr, minuteStr, secondStr);

        if (newDate != null) {
            return new java.sql.Time(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Makes a java.sql.Time from separate ints for hour, minute, and second.
     *
     * @param hour The hour int
     * @param minute The minute int
     * @param second The second int
     * @return A java.sql.Time made from separate ints for hour, minute, and
     *         second.
     */
    public static java.sql.Time toSqlTime(int hour, int minute, int second) {
        Date newDate = toDate(0, 0, 0, hour, minute, second);

        if (newDate != null) {
            return new java.sql.Time(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Converts a date and time String into a Timestamp
     *
     * @param dateTime A combined data and time string in the format
     *            "MM/DD/YYYY HH:MM:SS", the seconds are optional
     * @return The corresponding Timestamp
     */
    public static Timestamp toTimestamp(String dateTime) {
        Date newDate = toDate(dateTime);

        if (newDate != null) {
            return new Timestamp(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Converts a date String and a time String into a Timestamp
     *
     * @param date The date String: MM/DD/YYYY
     * @param time The time String: either HH:MM or HH:MM:SS
     * @return A Timestamp made from the date and time Strings
     */
    public static Timestamp toTimestamp(String date, String time) {
        Date newDate = toDate(date, time);

        if (newDate != null) {
            return new Timestamp(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Makes a Timestamp from separate Strings for month, day, year, hour,
     * minute, and second.
     *
     * @param monthStr The month String
     * @param dayStr The day String
     * @param yearStr The year String
     * @param hourStr The hour String
     * @param minuteStr The minute String
     * @param secondStr The second String
     * @return A Timestamp made from separate Strings for month, day, year,
     *         hour, minute, and second.
     */
    public static Timestamp toTimestamp(String monthStr, String dayStr, String yearStr, String hourStr,
            String minuteStr, String secondStr) {
        Date newDate = toDate(monthStr, dayStr, yearStr, hourStr, minuteStr, secondStr);

        if (newDate != null) {
            return new Timestamp(newDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Makes a Timestamp from separate ints for month, day, year, hour, minute,
     * and second.
     *
     * @param month The month int
     * @param day The day int
     * @param year The year int
     * @param hour The hour int
     * @param minute The minute int
     * @param second The second int
     * @return A Timestamp made from separate ints for month, day, year, hour,
     *         minute, and second.
     */
    public static Timestamp toTimestamp(int month, int day, int year, int hour, int minute, int second) {
        Date newDate = toDate(month, day, year, hour, minute, second);

        if (newDate != null) {
            return new Timestamp(newDate.getTime());
        } else {
            return null;
        }
    }

    public static Timestamp toTimestamp(Date date) {
        if (date == null)
            return null;
        return new Timestamp(date.getTime());
    }

    /**
     * Converts a date and time String into a Date
     *
     * @param dateTime A combined data and time string in the format
     *            "MM/DD/YYYY HH:MM:SS", the seconds are optional
     * @return The corresponding Date
     */
    public static Date toDate(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        // dateTime must have one space between the date and time...
        String date = dateTime.substring(0, dateTime.indexOf(" "));
        String time = dateTime.substring(dateTime.indexOf(" ") + 1);

        return toDate(date, time);
    }

    /**
     * Converts a date String and a time String into a Date
     *
     * @param date The date String: MM/DD/YYYY
     * @param time The time String: either HH:MM or HH:MM:SS
     * @return A Date made from the date and time Strings
     */
    public static Date toDate(String date, String time) {
        if (date == null || time == null)
            return null;
        String month;
        String day;
        String year;
        String hour;
        String minute;
        String second;

        int dateSlash1 = date.indexOf("/");
        int dateSlash2 = date.lastIndexOf("/");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
            return null;
        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0)
            return null;
        month = date.substring(0, dateSlash1);
        day = date.substring(dateSlash1 + 1, dateSlash2);
        year = date.substring(dateSlash2 + 1);
        hour = time.substring(0, timeColon1);

        if (timeColon1 == timeColon2) {
            minute = time.substring(timeColon1 + 1);
            second = "0";
        } else {
            minute = time.substring(timeColon1 + 1, timeColon2);
            second = time.substring(timeColon2 + 1);
        }

        return toDate(month, day, year, hour, minute, second);
    }

    /**
     * Makes a Date from separate Strings for month, day, year, hour, minute,
     * and second.
     *
     * @param monthStr The month String
     * @param dayStr The day String
     * @param yearStr The year String
     * @param hourStr The hour String
     * @param minuteStr The minute String
     * @param secondStr The second String
     * @return A Date made from separate Strings for month, day, year, hour,
     *         minute, and second.
     */
    public static Date toDate(String monthStr, String dayStr, String yearStr, String hourStr,
            String minuteStr, String secondStr) {
        int month, day, year, hour, minute, second;

        try {
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
            year = Integer.parseInt(yearStr);
            hour = Integer.parseInt(hourStr);
            minute = Integer.parseInt(minuteStr);
            second = Integer.parseInt(secondStr);
        } catch (Exception e) {
            return null;
        }
        return toDate(month, day, year, hour, minute, second);
    }

    /**
     * Makes a Date from separate ints for month, day, year, hour, minute, and
     * second.
     *
     * @param month The month int
     * @param day The day int
     * @param year The year int
     * @param hour The hour int
     * @param minute The minute int
     * @param second The second int
     * @return A Date made from separate ints for month, day, year, hour,
     *         minute, and second.
     */
    public static Date toDate(int month, int day, int year, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(year, month - 1, day, hour, minute, second);
            calendar.set(Calendar.MILLISECOND, 0);
        } catch (Exception e) {
            return null;
        }
        return new Date(calendar.getTime().getTime());
    }

    /**
     * Makes a date String in the given from a Date
     *
     * @param date The Date
     * @return A date String in the given format
     */
    public static String toDateString(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat dateFormat = null;
        if (format != null) {
            dateFormat = new SimpleDateFormat(format);
        } else {
            dateFormat = new SimpleDateFormat();
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        return dateFormat.format(date);
    }

    /**
     * Makes a date String in the format MM/DD/YYYY from a Date
     *
     * @param date The Date
     * @return A date String in the format MM/DD/YYYY
     */
    public static String toDateString(Date date) {
        return toDateString(date, "MM/dd/yyyy");
    }

    /**
     * Makes a time String in the format HH:MM:SS from a Date. If the seconds
     * are 0, then the output is in HH:MM.
     *
     * @param date The Date
     * @return A time String in the format HH:MM:SS or HH:MM
     */
    public static String toTimeString(Date date) {
        if (date == null)
            return "";
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        return (toTimeString(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)));
    }

    /**
     * Makes a time String in the format HH:MM:SS from a separate ints for hour,
     * minute, and second. If the seconds are 0, then the output is in HH:MM.
     *
     * @param hour The hour int
     * @param minute The minute int
     * @param second The second int
     * @return A time String in the format HH:MM:SS or HH:MM
     */
    public static String toTimeString(int hour, int minute, int second) {
        String hourStr;
        String minuteStr;
        String secondStr;

        if (hour < 10) {
            hourStr = "0" + hour;
        } else {
            hourStr = "" + hour;
        }
        if (minute < 10) {
            minuteStr = "0" + minute;
        } else {
            minuteStr = "" + minute;
        }
        if (second < 10) {
            secondStr = "0" + second;
        } else {
            secondStr = "" + second;
        }
        if (second == 0) {
            return hourStr + ":" + minuteStr;
        } else {
            return hourStr + ":" + minuteStr + ":" + secondStr;
        }
    }

    /**
     * Makes a combined data and time string in the format "MM/DD/YYYY HH:MM:SS"
     * from a Date. If the seconds are 0 they are left off.
     *
     * @param date The Date
     * @return A combined data and time string in the format
     *         "MM/DD/YYYY HH:MM:SS" where the seconds are left off if they are
     *         0.
     */
    public static String toDateTimeString(Date date) {
        if (date == null)
            return "";
        String dateString = toDateString(date);
        String timeString = toTimeString(date);

        if (dateString != null && timeString != null) {
            return dateString + " " + timeString;
        } else {
            return "";
        }
    }

    public static String toGmtTimestampString(Timestamp timestamp) {
        DateFormat df = DateFormat.getDateTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(timestamp);
    }

    /**
     * Makes a Timestamp for the beginning of the month
     *
     * @return A Timestamp of the beginning of the month
     */
    public static Timestamp monthBegin() {
        Calendar mth = Calendar.getInstance();

        mth.set(Calendar.DAY_OF_MONTH, 1);
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        mth.set(Calendar.MILLISECOND, 0);
        mth.set(Calendar.AM_PM, Calendar.AM);
        return new Timestamp(mth.getTime().getTime());
    }

    /**
     * returns a week number in a year for a Timestamp input
     *
     * @param input Timestamp date
     * @return A int containing the week number
     */
    public static int weekNumber(Timestamp input) {
        return weekNumber(input, TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * returns a day number in a week for a Timestamp input
     *
     * @param stamp Timestamp date
     * @return A int containing the day number (sunday = 1, saturday = 7)
     */
    public static int dayNumber(Timestamp stamp) {
        Calendar tempCal = toCalendar(stamp, TimeZone.getDefault(), Locale.getDefault());
        return tempCal.get(Calendar.DAY_OF_WEEK);
    }

    public static int weekNumber(Timestamp input, int startOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(startOfWeek);

        if (startOfWeek == Calendar.MONDAY) {
            calendar.setMinimalDaysInFirstWeek(4);
        } else if (startOfWeek == Calendar.SUNDAY) {
            calendar.setMinimalDaysInFirstWeek(3);
        }

        calendar.setTime(new Date(input.getTime()));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    // ----- New methods that take a timezone and locale -- //

    public static Calendar getCalendarInstance(TimeZone timeZone, Locale locale) {
        return Calendar.getInstance(timeZone, locale);
    }

    /**
     * Returns a Calendar object initialized to the specified date/time, time
     * zone, and locale.
     *
     * @param date date/time to use
     * @param timeZone
     * @param locale
     * @return Calendar object
     * @see java.util.Calendar
     */
    public static Calendar toCalendar(Date date, TimeZone timeZone, Locale locale) {
        Calendar cal = getCalendarInstance(timeZone, locale);
        if (date != null) {
            cal.setTime(date);
        }
        return cal;
    }

    /**
     * Perform date/time arithmetic on a Timestamp. This is the only accurate
     * way to perform date/time arithmetic across locales and time zones.
     *
     * @param stamp date/time to perform arithmetic on
     * @param adjType the adjustment type to perform. Use one of the
     *            java.util.Calendar fields.
     * @param adjQuantity the adjustment quantity.
     * @param timeZone
     * @param locale
     * @return adjusted Timestamp
     * @see java.util.Calendar
     */
    public static Timestamp adjustTimestamp(Timestamp stamp, int adjType, int adjQuantity, TimeZone timeZone,
            Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.add(adjType, adjQuantity);
        return new Timestamp(tempCal.getTimeInMillis());
    }

    public static Timestamp adjustTimestamp(Timestamp stamp, Integer adjType, Integer adjQuantity) {
        Calendar tempCal = toCalendar(stamp);
        tempCal.add(adjType, adjQuantity);
        return new Timestamp(tempCal.getTimeInMillis());
    }

    public static Timestamp getDayStart(Timestamp stamp, TimeZone timeZone, Locale locale) {
        return getDayStart(stamp, 0, timeZone, locale);
    }

    public static Timestamp getDayStart(Timestamp stamp, int daysLater, TimeZone timeZone, Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH), 0, 0,
                0);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
        Timestamp retStamp = new Timestamp(tempCal.getTimeInMillis());
        retStamp.setNanos(0);
        return retStamp;
    }

    public static Timestamp getDayEnd(Timestamp stamp, TimeZone timeZone, Locale locale) {
        return getDayEnd(stamp, Long.valueOf(0), timeZone, locale);
    }

    public static Timestamp getDayEnd(Timestamp stamp, Long daysLater, TimeZone timeZone, Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater.intValue());
        Timestamp retStamp = new Timestamp(tempCal.getTimeInMillis());
        retStamp.setNanos(0);
        // MSSQL datetime field has accuracy of 3 milliseconds and setting the
        // nano seconds cause the date to be rounded to next day
        // retStamp.setNanos(999999999);
        return retStamp;
    }

    public static Timestamp getWeekStart(Timestamp stamp, TimeZone timeZone, Locale locale) {
        return getWeekStart(stamp, 0, 0, timeZone, locale);
    }

    public static Timestamp getWeekStart(Timestamp stamp, int daysLater, TimeZone timeZone, Locale locale) {
        return getWeekStart(stamp, daysLater, 0, timeZone, locale);
    }

    public static Timestamp getWeekStart(Timestamp stamp, int daysLater, int weeksLater, TimeZone timeZone,
            Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH), 0, 0,
                0);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
        tempCal.set(Calendar.DAY_OF_WEEK, tempCal.getFirstDayOfWeek());
        tempCal.add(Calendar.WEEK_OF_MONTH, weeksLater);
        Timestamp retStamp = new Timestamp(tempCal.getTimeInMillis());
        retStamp.setNanos(0);
        return retStamp;
    }

    public static Timestamp getWeekEnd(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Timestamp weekStart = getWeekStart(stamp, timeZone, locale);
        Calendar tempCal = toCalendar(weekStart, timeZone, locale);
        tempCal.add(Calendar.DAY_OF_MONTH, 6);
        return getDayEnd(new Timestamp(tempCal.getTimeInMillis()), timeZone, locale);
    }

    public static Timestamp getMonthStart(Timestamp stamp, TimeZone timeZone, Locale locale) {
        return getMonthStart(stamp, 0, 0, timeZone, locale);
    }

    public static Timestamp getMonthStart(Timestamp stamp, int daysLater, TimeZone timeZone, Locale locale) {
        return getMonthStart(stamp, daysLater, 0, timeZone, locale);
    }

    public static Timestamp getMonthStart(Timestamp stamp, int daysLater, int monthsLater, TimeZone timeZone,
            Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), 1, 0, 0, 0);
        tempCal.add(Calendar.MONTH, monthsLater);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
        Timestamp retStamp = new Timestamp(tempCal.getTimeInMillis());
        retStamp.setNanos(0);
        return retStamp;
    }

    public static Timestamp getMonthEnd(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH),
                tempCal.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return getDayEnd(new Timestamp(tempCal.getTimeInMillis()), timeZone, locale);
    }

    public static Timestamp getYearStart(Timestamp stamp, TimeZone timeZone, Locale locale) {
        return getYearStart(stamp, 0, 0, 0, timeZone, locale);
    }

    public static Timestamp getYearStart(Timestamp stamp, int daysLater, TimeZone timeZone, Locale locale) {
        return getYearStart(stamp, daysLater, 0, 0, timeZone, locale);
    }

    public static Timestamp getYearStart(Timestamp stamp, int daysLater, int yearsLater, TimeZone timeZone,
            Locale locale) {
        return getYearStart(stamp, daysLater, 0, yearsLater, timeZone, locale);
    }

    public static Timestamp getYearStart(Timestamp stamp, Number daysLater, Number monthsLater, Number yearsLater,
            TimeZone timeZone, Locale locale) {
        return getYearStart(stamp, (daysLater == null ? 0 : daysLater.intValue()), (monthsLater == null ? 0
                : monthsLater.intValue()), (yearsLater == null ? 0 : yearsLater.intValue()), timeZone, locale);
    }

    public static Timestamp getYearStart(Timestamp stamp, int daysLater, int monthsLater, int yearsLater,
            TimeZone timeZone, Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        tempCal.add(Calendar.YEAR, yearsLater);
        tempCal.add(Calendar.MONTH, monthsLater);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
        Timestamp retStamp = new Timestamp(tempCal.getTimeInMillis());
        retStamp.setNanos(0);
        return retStamp;
    }

    public static Timestamp getYearEnd(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.getActualMaximum(Calendar.MONTH) + 1, 0, 0, 0, 0);
        return getMonthEnd(new Timestamp(tempCal.getTimeInMillis()), timeZone, locale);
    }

    public static int weekNumber(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar tempCal = toCalendar(stamp, timeZone, locale);
        return tempCal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Returns a List of day name Strings - suitable for calendar headings.
     *
     * @param locale
     * @return List of day name Strings
     */
    public static List<String> getDayNames(Locale locale) {
        Calendar tempCal = Calendar.getInstance(locale);
        tempCal.set(Calendar.DAY_OF_WEEK, tempCal.getFirstDayOfWeek());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", locale);
        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            resultList.add(dateFormat.format(tempCal.getTime()));
            tempCal.roll(Calendar.DAY_OF_WEEK, 1);
        }
        return resultList;
    }

    /**
     * Returns a List of month name Strings - suitable for calendar headings.
     *
     * @param locale
     * @return List of month name Strings
     */
    public static List<String> getMonthNames(Locale locale) {
        Calendar tempCal = Calendar.getInstance(locale);
        tempCal.set(Calendar.MONTH, Calendar.JANUARY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM", locale);
        List<String> resultList = new ArrayList<String>();
        for (int i = Calendar.JANUARY; i <= tempCal.getActualMaximum(Calendar.MONTH); i++) {
            resultList.add(dateFormat.format(tempCal.getTime()));
            tempCal.roll(Calendar.MONTH, 1);
        }
        return resultList;
    }

    /**
     * Returns an initialized DateFormat object.
     *
     * @param dateFormat optional format string
     * @param tz
     * @param locale can be null if dateFormat is not null
     * @return DateFormat object
     */
    public static DateFormat toDateFormat(String dateFormat, TimeZone tz, Locale locale) {
        DateFormat df = null;
        if (dateFormat == null) {
            df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        } else {
            df = new SimpleDateFormat(dateFormat);
        }
        df.setTimeZone(tz);
        return df;
    }

    /**
     * Returns an initialized DateFormat object.
     *
     * @param dateTimeFormat optional format string
     * @param tz
     * @param locale can be null if dateTimeFormat is not null
     * @return DateFormat object
     */
    public static DateFormat toDateTimeFormat(String dateTimeFormat, TimeZone tz, Locale locale) {
        DateFormat df = null;
        if (dateTimeFormat == null) {
            df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale);
        } else {
            df = new SimpleDateFormat(dateTimeFormat);
        }
        df.setTimeZone(tz);
        return df;
    }

    /**
     * Returns an initialized DateFormat object.
     *
     * @param timeFormat optional format string
     * @param tz
     * @param locale can be null if timeFormat is not null
     * @return DateFormat object
     */
    public static DateFormat toTimeFormat(String timeFormat, TimeZone tz, Locale locale) {
        DateFormat df = null;
        if (timeFormat == null) {
            df = DateFormat.getTimeInstance(DateFormat.MEDIUM, locale);
        } else {
            df = new SimpleDateFormat(timeFormat);
        }
        df.setTimeZone(tz);
        return df;
    }

    /**
     * Localized String to Timestamp conversion. To be used in tandem with
     * timeStampToString().
     */
    public static Timestamp stringToTimeStamp(String dateTimeString, TimeZone tz, Locale locale) throws ParseException {
        return stringToTimeStamp(dateTimeString, null, tz, locale);
    }

    /**
     * Localized String to Timestamp conversion. To be used in tandem with
     * timeStampToString().
     */
    public static Timestamp stringToTimeStamp(String dateTimeString, String dateTimeFormat, TimeZone tz, Locale locale)
            throws ParseException {
        DateFormat dateFormat = toDateTimeFormat(dateTimeFormat, tz, locale);
        Date parsedDate = dateFormat.parse(dateTimeString);
        return new Timestamp(parsedDate.getTime());
    }

    /**
     * Localized Timestamp to String conversion. To be used in tandem with
     * stringToTimeStamp().
     */
    public static String timeStampToString(Timestamp stamp, TimeZone tz, Locale locale) {
        return timeStampToString(stamp, null, tz, locale);
    }

    /**
     * Localized Timestamp to String conversion. To be used in tandem with
     * stringToTimeStamp().
     */
    public static String timeStampToString(Timestamp stamp, String dateTimeFormat, TimeZone tz, Locale locale) {
        DateFormat dateFormat = toDateTimeFormat(dateTimeFormat, tz, locale);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(stamp);
    }

    protected static List<TimeZone> availableTimeZoneList = null;



    /**
     * Returns a TimeZone object based upon an hour offset from GMT.
     *
     * @see java.util.TimeZone
     */
    public static TimeZone toTimeZone(int gmtOffset) {
        if (gmtOffset > 12 || gmtOffset < -14) {
            throw new IllegalArgumentException("Invalid GMT offset");
        }
        String tzId = gmtOffset > 0 ? "Etc/GMT+" : "Etc/GMT";
        return TimeZone.getTimeZone(tzId + gmtOffset);
    }

    public static int getSecond(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.SECOND);
    }

    public static int getMinute(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.MINUTE);
    }

    public static int getHour(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDayOfWeek(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfMonth(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static int getWeek(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getMonth(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.MONTH);
    }

    public static int getYear(Timestamp stamp, TimeZone timeZone, Locale locale) {
        Calendar cal = UtilDateTime.toCalendar(stamp, timeZone, locale);
        return cal.get(Calendar.YEAR);
    }

    public static Date getEarliestDate() {
        Calendar cal = getCalendarInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        cal.set(Calendar.YEAR, cal.getActualMinimum(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLatestDate() {
        Calendar cal = getCalendarInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        cal.set(Calendar.YEAR, cal.getActualMaximum(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date parseDate(String str) {
        try {
            return DateUtils.parseDate(str, PARSE_PATTERNS.toArray(new String[PARSE_PATTERNS.size()]));
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("无法解析日期：" + str, e);
        }
    }

    public static Date parseDate(String str, String dateFromat) {
        Set<String> patterns = new HashSet<String>();
        patterns.addAll(PARSE_PATTERNS);
        patterns.add(dateFromat);
        try {
            return DateUtils.parseDate(str, patterns.toArray(new String[patterns.size()]));
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("无法解析日期：" + str, e);
        }
    }

    public static Date getDayStart(String str) {
        Date date = parseDate(str);
        return getDayStart(new Timestamp(date.getTime()));
    }

    public static String getStrDayStart(String str) {
        Date startDate2 = getDayStart(str);
        return UtilDateTime.formatDate2String(startDate2);
    }
    public static String getStrDayEnd(String str) {
        Date date = getDayEnd(str);
        return UtilDateTime.formatDate2String(date);
    }


    public static Date getDayEnd(String str) {
        Date date = parseDate(str);
        return getDayEnd(new Timestamp(date.getTime()));
    }
    
    public static int getIntervalInDaysSofar(Date from) {
        return (int) ((nowDate().getTime() - from.getTime()) / (24 * 60 * 60 * 1000));
    }

    public static Calendar getStartCalendarOfToday() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY,0);
        startCalendar.set(Calendar.MINUTE,0);
        startCalendar.set(Calendar.SECOND,0);
        return startCalendar;
    }

    public static Calendar getLastCalendarOfToday() {
        Calendar startCalendar=getStartCalendarOfToday();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startCalendar.getTime());
        endCalendar.set(Calendar.HOUR_OF_DAY,23);
        endCalendar.set(Calendar.MINUTE,59);
        endCalendar.set(Calendar.SECOND,59);
        return endCalendar;
    }

    public static Calendar getStartCalendarOfYesterday() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.DATE,-1);
        startCalendar.set(Calendar.HOUR_OF_DAY,0);
        startCalendar.set(Calendar.MINUTE,0);
        startCalendar.set(Calendar.SECOND,0);
        return startCalendar;
    }


    public static Calendar getLastCalendarOfYesterday() {
        Calendar startCalendar= getStartCalendarOfYesterday();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startCalendar.getTime());
        endCalendar.set(Calendar.HOUR_OF_DAY,23);
        endCalendar.set(Calendar.MINUTE,59);
        endCalendar.set(Calendar.SECOND,59);
        return endCalendar;
    }

    public static Calendar getStartCalendarOfTomorrow() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.DATE,1);
        startCalendar.set(Calendar.HOUR_OF_DAY,0);
        startCalendar.set(Calendar.MINUTE,0);
        startCalendar.set(Calendar.SECOND,0);
        return startCalendar;
    }


    public static Calendar getLastCalendarOfTomorrow() {
        Calendar startCalendar= getStartCalendarOfTomorrow();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startCalendar.getTime());
        endCalendar.set(Calendar.HOUR_OF_DAY,23);
        endCalendar.set(Calendar.MINUTE,59);
        endCalendar.set(Calendar.SECOND,59);
        return endCalendar;
    }

    public static Calendar getStartCalendar(int date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.DATE,date);
        startCalendar.set(Calendar.HOUR_OF_DAY,0);
        startCalendar.set(Calendar.MINUTE,0);
        startCalendar.set(Calendar.SECOND,0);
        return startCalendar;
    }

    public static String dateToString(Date date,String format) {
        if(date==null){
             return "";
        }
        DateFormat format1 = new SimpleDateFormat(format);
        return format1.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date,"yyyy-MM-dd HH:mm:ss");
    }

    public static Boolean currentDateDeviationIsThisRange(Date date,int senconds) {
        Calendar cuurnetCalendar1 = Calendar.getInstance();
        cuurnetCalendar1.setTime(date);
        cuurnetCalendar1.add(Calendar.SECOND,senconds);
        Calendar cuurnetCalendar2 = Calendar.getInstance();
        cuurnetCalendar2.setTime(date);
        cuurnetCalendar2.add(Calendar.SECOND,-senconds);
        Date currentDate=new Date();
        return currentDate.after(cuurnetCalendar2.getTime()) && currentDate.before(cuurnetCalendar1.getTime());
    }

    public static Date addMonth(Date date, int addMonth) {
        return addInteger(date, Calendar.MONTH, addMonth);
    }

    public static Date plusMonth(Date date, int plusMonth) {
        return plusInteger(date, Calendar.MONTH, plusMonth);
    }

    public static Date addWeek(Date date, int addWeek) {
        return addInteger(date, Calendar.WEEK_OF_YEAR, addWeek);
    }

    public static Date plusWeek(Date date, int plusWeek) {
        return plusInteger(date, Calendar.WEEK_OF_YEAR, plusWeek);
    }

    public static Date plusHour(Date date, int plusWeek) {
        return plusInteger(date, Calendar.HOUR, plusWeek);
    }

    public static Date plusDay(Date date, int plusWeek) {
        return plusInteger(date, Calendar.DAY_OF_MONTH, plusWeek);
    }

    public static void main(String[] args) {
        Date date=new Date();
        System.out.println(date);
        System.out.println(UtilDateTime.plusDay(date, 1));
    }


    public static Date addInteger(Date date, int dateType, int amount) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            date = calendar.getTime();
        }
        return date;
    }

    /**
     *  date 增加
     *
     * @param date
     * @param dateType 增加的类型
     * @param amount
     * @return
     */
    private static Date plusInteger(Date date, int dateType, int amount) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, (0 - amount));
            date = calendar.getTime();
        }
        return date;
    }

    /**
     *  增加 天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return addInteger(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 返回某年某月中的最大天
     */
    public static int getMaxDay(int iyear,int imonth){
        int day = 0;
        try{
            if(imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12){
                day = 31;
            } else if(imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11){
                day = 30;
            } else if((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))){
                day = 29;
            } else{
                day = 28;
            }
            return day;
        } catch(Exception e){
            return day;
        }
    }

    public static final long MIN_MILLI_SECOND = 60 * 1000;
    public static final long HOUR_MILLI_SECOND = 60 * MIN_MILLI_SECOND;
    public static final long DAY_MILLI_SECOND = 24 * HOUR_MILLI_SECOND;

    public static String getTimeInfo(Date targetTime){
        Date now = nowDate();
        long l = now.getTime() - targetTime.getTime();
        String timePrefixStr = "前";
        if(l < 0){
            timePrefixStr = "后";
            l = 0 - l;
        }
        long day = l/DAY_MILLI_SECOND;
        long hour = (l/HOUR_MILLI_SECOND - day*24);
        long min = ((l/MIN_MILLI_SECOND) - day*24*60 - hour*60);

        StringBuffer sb = new StringBuffer();
        if(day > 0){
            if(day >= 10){
                sb.append(formatDate2String(targetTime, "yyyy-MM-dd"));
                timePrefixStr = "";
            } else {
                sb.append(day+"天");
            }
        } else if (hour > 0) {
            sb.append(hour + "小时");
        } else {
            if(min <= 0){
                min = 1;
            }
            sb.append(min + "分");
        }
        sb.append(timePrefixStr);
        return sb.toString();
    }

    public static String getDateString(String str){
        return  formatDate2String(getDayStart(new Timestamp(parseDate(str).getTime())));
    }
}
