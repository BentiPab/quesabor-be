package com.queempanadas.utils;

import com.queempanadas.model.Shift;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class DateUtils {

    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public LocalDateTime parseStringToLocalDateTime(String dateString) {
        LocalDateTime dateTime = LocalDate.parse(dateString, formatterDate)
                .atStartOfDay();
        return dateTime;
    }

    public String parseLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(formatterDate);
    }

    public String parseLocalDateTimeToStringWithTime(LocalDateTime dateTime) {
        return dateTime.format(formatterDateTime);
    }

    public Shift parseLocalDateTimeToShift(LocalDateTime dateTime) {
        int hour = dateTime.getHour();

        if (hour >= 8 && hour < 12) {
            return Shift.MORNING;
        }

        if (hour >= 12 && hour < 15) {
            return Shift.LAUNCH;
        }

        if (hour >= 15 && hour < 19) {
            return Shift.EVENING;
        }

        return Shift.NIGHT;
    }

    public List<LocalDateTime> getDatesGap(String type) {
        LocalDateTime initialDate = LocalDate.now().atStartOfDay();
        LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime weekStartDate = initialDate.minusDays(initialDate.getDayOfWeek()
                .getValue() - 1);
        LocalDateTime weekEndDate = endDate.plusDays(7 - initialDate.getDayOfWeek()
                .getValue());
        LocalDateTime monthStartDate = initialDate.with(firstDayOfMonth());
        LocalDateTime monthEndDate = endDate.with(lastDayOfMonth());

        return switch (type) {
            case "week":
                yield List.of(weekStartDate, weekEndDate);
            case "month":
                yield List.of(monthStartDate, monthEndDate);
            default:
                yield List.of(initialDate, endDate);
        };
    }

}
