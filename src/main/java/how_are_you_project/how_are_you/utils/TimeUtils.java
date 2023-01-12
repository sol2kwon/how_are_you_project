package how_are_you_project.how_are_you.utils;


import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
public class TimeUtils {
    /***
     * LocalDateTime to java.util.Date 변환
     * @param localDateTime 변환할 LocalDateTime 객체
     * @return java.util.Date
     */
    public Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return instantToDate(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /***
     * LocalDate to java.util.Date 변환
     * @param localDate 변환할 LocalDate 객체
     * @return java.util.Date
     */
    public Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return instantToDate(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /***
     * 두 날짜간 시간(h) 차이 계산
     * @param start
     * @param end
     * @return 시간
     */
    public long diffHours(LocalDateTime start, LocalDateTime end) {
        return Math.abs(Duration.between(end, start).toHours());
    }

    /***
     * ms to LocalDateTime
     * @param ms ms
     * @return LocalDateTime
     */
    public LocalDateTime fromMills(long ms) {
        return Instant.ofEpochSecond(ms).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /***
     * text to LocalDateTime
     * @param text text
     * @param pattern pattern
     * @return LocalDateTime
     */
    public LocalDateTime fromText(String text, String pattern) {
        return LocalDateTime.parse(text, pattern(pattern));
    }

    /***
     * Now to Formatted String
     * @param pattern format
     * @return Formatted String
     */
    public String format(String pattern) {
        return format(LocalDateTime.now(), pattern);
    }

    /***
     * LocalDateTime to Formatted String
     * @param at at
     * @param pattern pattern
     * @return Formatted String
     */
    public String format(LocalDateTime at, String pattern) {
        return at.format(pattern(pattern));
    }

    /***
     * at between start, end
     * @param at at
     * @param start start
     * @param end end
     * @return true/false
     */
    public boolean isBetween(LocalDateTime at, LocalDateTime start, LocalDateTime end) {
        return at.isAfter(start) && at.isBefore(end);
    }

    public boolean isOverlap(LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2) {
        return start.isBefore(end2) && start2.isBefore(end);
    }

    public boolean isOverlap(LocalDate start, LocalDate end, LocalDate start2, LocalDate end2) {
        return start.isBefore(end2) && start2.isBefore(end);
    }

    public boolean isOverlap(LocalTime start, LocalTime end, LocalTime start2, LocalTime end2) {
        return start.isBefore(end2) && start2.isBefore(end);
    }

    private Date instantToDate(Instant instant) {
        return Date.from(instant);
    }

    private DateTimeFormatter pattern(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
