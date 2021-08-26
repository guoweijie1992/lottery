package com.hzsmk.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间工具类
 *
 * @author jiangjh
 * @date 2020/1/9 17:31
 */
@Slf4j
public final class DateUtil {

    /**
     * 年月日yyyyMMdd
     */
    public static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 年月yyyyMM
     */
    public static final DateTimeFormatter YYYYMM = DateTimeFormatter.ofPattern("yyyyMM");
    /**
     * 时分HH:mm
     */
    public static final DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern("HHmmss");

    /**
     * 年月日时分秒
     */
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");



    public static final SimpleDateFormat sdfym = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat sdfymd = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat sdfymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            log.info("system error:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        try {
            return now.format(TIME);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取形如yyyy-MM-dd格式的当前天
     *
     * @return
     */
    public static String currentDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }


    /**
     * 获取yyyyMMdd格式的明天
     *
     * @return
     */
    public static String nextDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(1).format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取yyyyMMdd格式的昨天
     *
     * @return
     */
    public static String yesterDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(-1).format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取yyyyDD日格式的时间月初
     * @param source
     * @return
     */
    public static String formatYYYYDD(String source) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
            SimpleDateFormat sdf2 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(source);
            Calendar calendar = Calendar.getInstance ();
            calendar.setTime(date);
            return sdf2.format (calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取yyyyDD日格式的时间月初
     * @param source
     * @return
     */
    public static String first(String source) {
        try {
            SimpleDateFormat sdf = sdfym;
            SimpleDateFormat sdf2 = sdfymd;
            Date date = sdf.parse(source);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return sdf2.format (calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取yyyyDD日格式的时间月初
     * @param source
     * @return
     */
    public static String monthEnd(String source) {
        try {
            SimpleDateFormat sdf = sdfym;
            SimpleDateFormat sdf2 = sdfymd;
            Date date = sdf.parse(source);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.roll(Calendar.DAY_OF_MONTH, -1);
            return sdf2.format (calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上个月
     *
     * @return
     */
    public static String lastMonth() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusMonths(-1).format(YYYYMM);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 修改月份
     *
     * @return
     */
    public static String getChangedDate(int month, int day, String format) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            return now.plusDays(day).plusMonths(month).format(formatter);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取yyyyMMdd格式最近7天
     * 升序排列
     *
     * @return
     */
    public static List<String> nearSevenDay() {
        List<String> list = new ArrayList<>();
        try {
            int days = 7;
            LocalDate now = LocalDate.now();
            for (int i = 0; i < days; i++) {
                list.add(now.format(YYYYMMDD));
                now = now.plusDays(-1);
            }
        } catch (Exception e) {
            log.error("system error:{}", e);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 获取yyyyMMdd格式最近days天
     * 升序排列
     *
     * @return
     */
    public static List<String> nearDays(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("获取最近天数参数有误!");
        }
        List<String> list = new ArrayList<>();
        try {
            LocalDate now = LocalDate.now();
            for (int i = 0; i < days; i++) {
                list.add(now.format(YYYYMMDD));
                now = now.plusDays(-1);
            }
        } catch (Exception e) {
            log.error("system error:{}", e);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 获取现在的时分 形如HH:MM
     *
     * @return
     */
    public static String currentHourMinuteSecond() {
        LocalTime now = LocalTime.now();
        try {
            return now.format(HHMMSS);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取从开始时间到结束时间范围内的时间
     *
     * @param startDay 格式YYYYMMDD
     * @param endDay   格式YYYYMMDD
     * @return
     */
    public static List<String> startEndDay(String startDay, String endDay) {
        if (StringUtils.isNoneBlank(startDay, endDay)) {
            List<String> list = new ArrayList<>();
            try {
                LocalDate start = LocalDate.parse(startDay, YYYYMMDD);
                LocalDate end = LocalDate.parse(endDay, YYYYMMDD);
                if (start.isAfter(end)) {
                    throw new IllegalArgumentException("获取startEndDay, 开始日期不能大于结束日期!");
                }
                while (start.isBefore(end)) {
                    list.add(start.format(YYYYMMDD));
                    start = start.plusDays(1);
                }
                list.add(endDay);
            } catch (Exception e) {
                log.error("system error:{}", e);
            }
            return list;
        } else {
            throw new IllegalArgumentException("获取startEndDay参数有误!");
        }
    }

    /**
     * 获取从开始时间到结束时间范围内的时间
     *
     * @param startMonth 格式YYYYMM
     * @param endMonth   格式YYYYMM
     * @return
     */
    public static List<String> startEndMonth(String startMonth, String endMonth) {
        if (StringUtils.isNoneBlank(startMonth, endMonth)) {
            List<String> list = new ArrayList<>();
            try {
                YearMonth start = YearMonth.parse(startMonth, YYYYMM);
                YearMonth end = YearMonth.parse(endMonth, YYYYMM);
                if (start.isAfter(end)) {
                    throw new IllegalArgumentException("获取startEndMonth, 开始日期不能大于结束日期!");
                }
                while (start.isBefore(end)) {
                    list.add(start.format(YYYYMM));
                    start = start.plusMonths(1);
                }
                list.add(endMonth);
            } catch (Exception e) {
                log.error("system error:{}", e);
            }
            return list;
        } else {
            throw new IllegalArgumentException("获取startEndMonth参数有误!");
        }
    }

    public static void main(String[] args) {
        System.out.println(currentTime());
        System.out.println(currentDay());
        System.out.println(nextDay());
        System.out.println(yesterDay());
        System.out.println(nearSevenDay());
        System.out.println(nearDays(5));
        System.out.println(currentHourMinuteSecond());
        System.out.println(startEndDay("20200302", "20200310"));
        System.out.println(lastMonth());
    }

    public static Date fomat2Date(String date, String sformat) {
        DateFormat fmt = new SimpleDateFormat(sformat);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
