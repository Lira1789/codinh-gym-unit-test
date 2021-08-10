package com.codinggym.analyzers;

import com.codinggym.models.Message;

import java.util.List;

public class EmployeeAnalyzer {

    private EmployeeAnalyzer() {
    }

    public static EmployeeAnalyzer getInstance() {
        return new EmployeeAnalyzer();
    }

    // false - if employee has less than  70% work related messages and more than 10% alarmed messages
    public boolean isReliableEmployee(List<Message> messages) {

        int size = messages.size();

        long workRelatedMessages = messages.stream().filter(Message::isWorkRelatedMessage).count();
        long alarmedMessages = messages.stream().filter(Message::isAlarmedMessage).count();

        double alarmMessagesPercent = ((double) alarmedMessages / (double) size) * 100;
        double workRelatedMessagesPercent = ((double) workRelatedMessages / (double) size) * 100;

        return workRelatedMessagesPercent >= 70.00 && alarmMessagesPercent <= 10.00;

    }
}
