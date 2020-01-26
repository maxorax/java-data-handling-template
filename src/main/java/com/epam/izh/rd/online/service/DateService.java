package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public interface DateService {

    String parseDate(LocalDate localDate);

    LocalDateTime parseString(String string);

    String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter);

    long getNextLeapYear();

    long getSecondsInYear(int year);
}
