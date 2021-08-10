package com.codinggym.analyzers;

import com.codinggym.exceptions.ShortMessageException;

import java.util.Arrays;
import java.util.List;

public class MessageAnalyzer {

    private static final List<String> WORK_RELATED_WORDS = List.of("Jira", "build", "release", "story", "Jenkins", "slack", "letter", "PR", "approved");
    private static final List<String> ALARM_WORDS = List.of("LinkedIn", "VC", "resume", "salary", "interview");

    private MessageAnalyzer() {
    }

    public static MessageAnalyzer getInstance() {
        return new MessageAnalyzer();
    }

    public int countWords(String message) {

        if (message == null || message.isEmpty()) {
            return 0;
        }

        return message.split(" ").length;
    }

    public boolean isWorkRelatedMessage(String message) {

        if (countWords(message) < 2) {
            throw new ShortMessageException();
        }

        long count = Arrays.stream(message.split(" ")).filter(WORK_RELATED_WORDS::contains).count();

        return count > 1;
    }

    public boolean isAlarmMessage(String message) {

        if (countWords(message) < 1) {
            throw new ShortMessageException();
        }

        long count = Arrays.stream(message.split(" ")).filter(ALARM_WORDS::contains).count();

        return count > 0;

    }

    public void analyze() {
        try {
            //some logic
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
