package cc.seeed.sensecap.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间 Util
 *
 * @author jancee
 */
public class DateUtils {

    static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_STR = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String BEFORE_DATE_KEY_STR = "yyyyMMdd";

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(long date1) {
        long date2 = dateToUnixTimestamp();
        if (date1 > date2) {
            return 1;
        } else if (date1 < date2) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 获取系统当前计费期
     *
     * @return
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
        return df.format(new Date());
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return timestamp;
    }

    public static long dateTimeToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            SimpleDateFormat sd = new SimpleDateFormat(DATE_TIME_STR);
            sd.setTimeZone(TimeZone.getTimeZone("GMT+0"));
            timestamp = sd.parse(date).getTime();

        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return timestamp;
    }

    public static String UnixTimestampToDateTime(Long timestamp) {

        SimpleDateFormat sd = new SimpleDateFormat(DATE_TIME_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return sd.format(new Date(timestamp));
    }


    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        long timestamp = 0;
        try {
            timestamp = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }


    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        //sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }


    public static String unixTimestampToDate(long timestamp, String pattern) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        //sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }


    public static String UTCTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return sd.format(new Date(timestamp));
    }

    public static String UTCTimestampToDate(long timestamp, String pattern) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return sd.format(new Date(timestamp));
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(int days) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(DATE_SMALL_STR);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDateByTime(long time, int days) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        Date date = new Date(time);
        canlendar.setTime(date);
        int day = canlendar.get(Calendar.DATE);
        canlendar.set(Calendar.DATE, day + days); // 日期减 如果不够减会将月变动
        SimpleDateFormat sdfd = new SimpleDateFormat(DATE_SMALL_STR);
        String dateStr = sdfd.format(canlendar.getTime());

        return dateStr;
    }


    public static String getBeforeDayDate(int days) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, -days); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(BEFORE_DATE_KEY_STR);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    public static String getBeforeDayByDate(String yyyyMMdd) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        Date date = null;
        try {
            date = new SimpleDateFormat(BEFORE_DATE_KEY_STR).parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        canlendar.setTime(date);
        int day = canlendar.get(Calendar.DATE);
        canlendar.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat(BEFORE_DATE_KEY_STR).format(canlendar.getTime());
        return dayBefore;
    }

    /**
     * 得到n天之后的日期
     *
     * @param month
     * @return
     */
    public static String getAfterMonthDate(int month) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.MONTH, month); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM");
        String dateStr = sdfd.format(date);

        return dateStr;
    }


    /**
     * @return
     */
    public static String format(Date date, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }


    /**
     * 获取当天零时时间戳
     *
     * @return
     */
    public static long getHourTime(String day, int hour) {
        SimpleDateFormat sd = new SimpleDateFormat(BEFORE_DATE_KEY_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date = null;
        try {
            date = sd.parse(day);
            //System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        long time = zero.getTime();
        return time;
    }


    public static void main(String[] args) {

        System.out.println(dateTimeToUnixTimestamp("2020-06-16T09:52:04.378Z"));
        System.out.println(UnixTimestampToDateTime(1592301124378L));
        //long now = System.currentTimeMillis();
        // System.out.println(getHourTime("20200611",24));
        // System.out.println(UTCTimestampToDate(1591920000000L));
        //System.out.println(daysBetween(System.currentTimeMillis()-24*60*60*1000,System.currentTimeMillis()));
        //Date yyyyMMdd = parse("20200324", "yyyyMMdd");

        //String s = UTCTimestampToDate(now);
        //String s1 = unixTimestampToDate(now);
        //System.out.println(s+" < --- > "+s1);

        //System.out.println(getBeforeDayByDate("20200530"));
        //System.out.println(getBeforeDayDate(32));
        //System.out.println(dateToUnixTimestamp("20200531","yyyyMMdd"));
        //System.out.println(getAfterDayDateByTime(now,280));


    }

}
