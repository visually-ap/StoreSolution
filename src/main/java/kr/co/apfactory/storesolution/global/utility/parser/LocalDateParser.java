package kr.co.apfactory.storesolution.global.utility.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocalDateParser {
    // 해당날짜의 시작시간(ex:2018-06-15 00:00:00)
    public static LocalDateTime getStartDatetimeFromStringDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
    }

    // 해당날짜의 시작시간(ex:2018-06-15 00:00:00)
    public static LocalDateTime getStartDatetimeFromDate(LocalDate date) {
        return date.atStartOfDay();
    }

    // 해당날짜의 끝 시간(ex:2018-06-15 23:59:59)
    public static LocalDateTime getEndDatetimeFromStringDate(String date) {
        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.of(23, 59, 59));
    }

    // 해당날짜의 끝 시간(ex:2018-06-15 23:59:59)
    public static LocalDateTime getEndDatetimeFromDate(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.of(23, 59, 59, 999999999));
    }

    // 해당날짜의 1일
    public static LocalDate getFirstDate(LocalDate today) {
        return today.withDayOfMonth(1);
    }

    // 해당날짜의 말일
    public static LocalDate getLastDate(LocalDate today) {
        return today.withDayOfMonth(today.lengthOfMonth());
    }

    /*
        문자열 일시 -> LocalDateTime
        format : yyyy-MM-dd HH:mm:ss, yyyy-MM-dd HH:mm 등
    */
    public static LocalDateTime getDatetimeFromStringDatetime(String datetime, String format) {
        // date가 비어있으면 null
        if (datetime.equals("")) {
            return null;
        }
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(format));
    }

    /*
        문자열 날짜 -> LocalDate
        format : yyyy-MM-dd, yyyyMMdd등
    */
    public static LocalDate getDateFromStringDate(String date, String format) {
        // date가 비어있으면 null
        if (date.equals("")) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static int getPeriod(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getDays();
    }

    /**
     * 휴일 시작일과 종료일로부터 휴일 목록 구하기
     */
    public static List<LocalDate> getHolidayList(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> holidayList = new ArrayList<>();
        int diff = getPeriod(startDate, endDate);
        LocalDate nextDate = startDate;
        holidayList.add(nextDate);
        for (int i = 1; i < diff; i++) {
            nextDate = nextDate.plusDays(1);
            holidayList.add(nextDate);
        }

        return holidayList;
    }

    /**
     * 시작일부터 워킹 데이(공휴일, 회사 지정 휴일)로부터 x일이 지난 날짜
     */
    public static LocalDate getAfterWorkingDay(LocalDate start, int afterDay) {
        return null;
    }
}
