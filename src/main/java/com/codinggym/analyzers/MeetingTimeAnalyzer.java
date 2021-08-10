package com.codinggym.analyzers;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MeetingTimeAnalyzer {

    private MeetingTimeAnalyzer() {
    }

    public static MeetingTimeAnalyzer getInstance() {
        return new MeetingTimeAnalyzer();
    }

    public boolean isEnoughMeetingTime(LocalDate date, int meetingMinutes) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        boolean isEnoughMeetingTime;
        switch (dayOfWeek) {
            case MONDAY:
                isEnoughMeetingTime = meetingMinutes > 90;
                break;
            case TUESDAY:
                isEnoughMeetingTime = meetingMinutes > 120;
                break;
            case WEDNESDAY:
                isEnoughMeetingTime = meetingMinutes > 150;
                break;
            case THURSDAY:
                isEnoughMeetingTime = meetingMinutes > 130;
                break;
            case FRIDAY:
                isEnoughMeetingTime = meetingMinutes > 100;
                break;
            default:
                isEnoughMeetingTime = true;
        }

        return isEnoughMeetingTime;
    }
}
