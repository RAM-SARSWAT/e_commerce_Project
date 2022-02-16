/*************************************************************************
 *
 * SATIN CREDITCARE NETWORK LIMITED CONFIDENTIAL
 * __________________
 *
 *  [2018] SATIN CREDITCARE NETWORK LIMITED
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of SATIN CREDITCARE NETWORK LIMITED, and
 * The intellectual and technical concepts contained herein are proprietary to SATIN CREDITCARE NETWORK LIMITED
 * and may be covered by India and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission
 * is obtained from SATIN CREDITCARE NETWORK LIMITED.
 */
package com.ECA.utils;


import com.ECA.Constant.Constant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.MINUTES;

public class DateUtil {

    public static final String BASE_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMATS = "dd-MM-yy";
    public static final String BASE_TIME_FORMAT = "HH:mm";
    public static final String BASE_DATE_FORMATTER = "ddMMyyyy";

    public static final String BASE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public static final String DD_MM_YYYY_HH_MM = "dd-MM-yyyy hh:mm";

    public static final String YYYY_MM = "yyyyMM";

    public static final int MIN_IN_HOUR = 60;

    private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    static SimpleDateFormat sdf = new SimpleDateFormat(BASE_DATE_FORMAT);

    public static Date parseDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        return sdf.parse(date);
    }

    public static LocalDate parseDateCell(Object cell) {
        return LocalDate.parse(cell.toString(), new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yyyy").toFormatter(Locale.UK));
    }

    public static String parseStringDate(String date, String format) {
        try {
            String temp = date.replace("00:00:00.0", "");
            temp = temp.replace(" ", "");
            LocalDate localDate = LocalDate.parse(temp);
            return getLocalDateToString(localDate, format);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static LocalDate parseLocalDate(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

        public static String dateWithTime()
        {

            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat(Constant.DATE_TIME_FORMAT);
            String today = formatter.format(date);
            return today;
        }

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getDateFromMillis(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static Date getDateBeforeXDays(Date date, int getDateBeforeXDays) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -getDateBeforeXDays);
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static String getDateFromInstant(Instant instantDate, String format) {
        LocalDateTime datetime = LocalDateTime.ofInstant(instantDate, ZoneOffset.UTC);
        return DateTimeFormatter.ofPattern(format).format(datetime);
    }

    public static LocalDate getLocalDateFromInstant(Integer emiDay) {
        return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), emiDay).plusMonths(1);
    }

    public static String getLocalDateToString(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static String getLocalDateTimeToString(LocalDateTime localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static String getDateToString(Date date, String format) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(date);
    }

    public static long getDaysFromLocalDatedifference(LocalDate fromDate, LocalDate toDate) {
        try {
            return ChronoUnit.DAYS.between(fromDate, toDate) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Boolean isVali24HourTime(String time) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(TIME24HOURS_PATTERN);
        matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static String getLocalTimeToString(LocalTime time, String formatter) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatter);
        return time.format(format);
    }

    public static LocalTime getStringToLocalTime(String time, String formatter) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatter);
        return LocalTime.parse(time, format);
    }

    public static boolean isSecondOrFourthSaturday(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfMonth());
        if (weekNumber == 2 || weekNumber == 4) {
            return true;
        }
        return false;
    }

    public static boolean isSecondOrThirdSaturday(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfMonth());
        if (weekNumber == 2 || weekNumber == 3) {
            return true;
        }
        return false;
    }

    public static String formatLocalDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date);
    }

    public static LocalDateTime getLocalDateTimeFromString(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate ld = LocalDate.parse(date, formatter);
        return LocalDateTime.of(ld, LocalTime.of(0, 0));
    }

    public static LocalDate getLocalDateFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static long getTotalNumberOfDays(LocalDate date) {
        return date.lengthOfMonth();
    }

    public static long getNumberOfDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    public static List<LocalDate> getDateRange(Date startDate, Date endDate) throws ParseException {
        LocalDate start = getLocalDateFromDate(startDate);
        LocalDate end = getLocalDateFromDate(endDate);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        return totalDates;
    }

    public static Date getFirstDateOfNextMonth(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getFirstDateOfPreviousMonth(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getXDaysDateOfPreviousMonth(Date date, int days) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static long localTimeDifferenceInMinutes(LocalTime startTime, LocalTime endTime) {
        return MINUTES.between(startTime, endTime);
    }

    public static LocalDateTime combineLocalDateAndLocaTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    public static LocalTime getTime(Integer timeInMinutes) {
        if (timeInMinutes == null)
            return null;
        int hour = timeInMinutes / 60;
        int minutes = timeInMinutes % 60;
        return LocalTime.of(hour, minutes);
    }

    public static List<LocalDate> getDateRange(String startDate, String endDate) throws ParseException {
        return getDateRange(DateUtil.parseDate(startDate, BASE_DATE_FORMAT),
                DateUtil.parseDate(endDate, BASE_DATE_FORMAT));
    }

    public static LocalDate stringToDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(BASE_DATE_FORMAT);
        LocalDate dt = LocalDate.now();
        dt = LocalDate.parse(date, dtf);
        return dt;
    }
}
