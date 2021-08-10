package com.codinggym.services;

import com.codinggym.exceptions.WeekendException;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class TimeService {

    public boolean isTimeForMonitoring(LocalDate date) {

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            throw new WeekendException();
        }

        return dayOfWeek.equals(DayOfWeek.FRIDAY);
    }
}
