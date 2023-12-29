package com.queempanadas.utils;

import com.queempanadas.model.Shift;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateUtils {

    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public LocalDateTime parseStringToLocalDateTime(String dateString) {
        LocalDateTime dateTime = LocalDate.parse(dateString, formatterDate).atStartOfDay();
        return  dateTime;
    }

    public String parseLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(formatterDate);
    }

    public String parseLocalDateTimeToStringWithTime(LocalDateTime dateTime) {
        return dateTime.format(formatterDateTime);
    }

    public Shift parseLocalDateTimeToShift(LocalDateTime dateTime) {
       int hour = dateTime.getHour();

       if (hour >=8 && hour < 12) {
           return Shift.MORNING;
       }

       if (hour >=12 && hour < 15) {
           return  Shift.LAUNCH;
       }

       if (hour >= 15 && hour < 19) {
           return Shift.EVENING;
       }

           return Shift.NIGHT;
    }

}
