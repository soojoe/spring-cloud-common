package com.soojoe.common.date;

import com.soojoe.common.exception.ParamInvalidException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类：[日期]、[字符串]、[时间]三者的转换工具类 三者的转换是非常常用的，所以定义此工具类。三者的简要说明如下： [日期]：日期对象 [字符串]：指的是日期的字符串表示
 * [时间]：long型的值
 *
 * @author soojoe
 * @version 1.0.0
 * @date 2019/06/28 14:58
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATETIMEFORMAT = "yyyyMMddHHmmss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIMEFORMATSIMPLE = "yyyyMMdd";

    public static final long TIME_OF_MINUTE = 60000L;

    public static final long TIME_OF_HOUR = 3600000L;

    public static final long TIME_OF_DAY = 86400000L;

    /**
     * 将[日期]按照给定的[日期格式]转成[字符串]
     *
     * @param date 日期
     * @param format 日期格式
     */
    public static String parseDateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_TIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将[时间]按照指定的[日期格式]转成[字符串]
     *
     * @param time 时间
     * @param format 日期格式
     */
    public static String parseLongToString(long time, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        return mydate.format(new Date(time));
    }

    /**
     * 将[字符串]按照指定的[日期格式]转成[时间]
     *
     * @param time 字符串
     * @param format 日期格式
     */
    public static long parseStringToLong(String time, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        try {
            Date date = mydate.parse(time);
            if (date != null) {
                return date.getTime();
            }
        } catch (ParseException e) {
            logger.error("Date parse error:", e);
        }
        return 0;
    }

    /**
     * 将[字符串]按照指定的[日期格式]转成[日期]
     *
     * @param time 字符串
     * @param format 日期格式
     */
    public static Date parseStringToDate(String time, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        try {
            Date date = mydate.parse(time);
            if (date != null) {
                return date;
            }
        } catch (ParseException e) {
            logger.error("Date parse error:", e);
        }
        return null;
    }

    /**
     * 添加天数
     *
     * @param date 日期
     * @param amount 增加天数
     */
    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, amount);

        return calendar.getTime();
    }

    /**
     * 获取某天的开始时间
     *
     * @param date 日期
     */
    public static Date getStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某天的结束时间
     *
     * @param date 日期
     */
    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static String getCurrentTimestamp() {

        long timeStamp = System.currentTimeMillis();
        return String.valueOf(timeStamp);
    }

    public static String getCurrentTimestamp10() {

        long timeStamp = System.currentTimeMillis() / 1000;
        String timestr = String.valueOf(timeStamp);
        return timestr;
    }

    public static String getTimeStamp() {
        int time = (int) (System.currentTimeMillis() / 1000);
        return String.valueOf(time);
    }

    /**
     * 计算某时间周的开始和结束时间
     *
     * @param date 当前计算日期
     * @param offset 偏移的周数，-1上周，0本周，1下周
     * @param flag 0：周的开始时间 1：周的结束时间
     *
     * <p><strong>注意：</strong>一周以（周一～周日）为标准</p>
     * @return 某时间点的毫秒级时间戳
     */
    public static long getWeekTimestamp(Date date,Integer offset,Integer flag){
        Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        gc.add(Calendar.WEEK_OF_YEAR, offset);
        int weekDay=gc.get(Calendar.DAY_OF_WEEK);
        int dayOffset=0;
        switch (flag){
            case 0:
                dayOffset=Math.abs(8-weekDay)%7-6;
                gc.add(Calendar.DAY_OF_MONTH,dayOffset);
                gc.set(Calendar.HOUR_OF_DAY, 0);
                gc.set(Calendar.MINUTE, 0);
                gc.set(Calendar.SECOND, 0);
                return gc.getTimeInMillis();
            case 1:
                dayOffset=Math.abs(8-weekDay)%7;
                gc.add(Calendar.DAY_OF_MONTH,dayOffset);
                gc.set(Calendar.HOUR_OF_DAY, 23);
                gc.set(Calendar.MINUTE, 59);
                gc.set(Calendar.SECOND, 59);
                return gc.getTimeInMillis();
            default: throw new ParamInvalidException("type is illegal");
        }
    }

    public static void main(String[] args) {
        Date now=new Date();
        //本周
        System.out.println(getWeekTimestamp(now,0,0));
        System.out.println(getWeekTimestamp(now,0,1));
        //上周
        System.out.println(getWeekTimestamp(now,-1,0));
        System.out.println(getWeekTimestamp(now,-1,1));
        //下周
        System.out.println(getWeekTimestamp(now,1,0));
        System.out.println(getWeekTimestamp(now,1,1));
    }
}
