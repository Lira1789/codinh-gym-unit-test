package com.codinggym.models;

import com.codinggym.analyzers.MessageAnalyzer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Message {

    private String text;
    private boolean isWorkRelatedMessage;
    private boolean isAlarmedMessage;

    public Message(String text) {
        MessageAnalyzer analyzer = MessageAnalyzer.getInstance();
        this.text = text;
        this.isWorkRelatedMessage = analyzer.isWorkRelatedMessage(text);
        this.isAlarmedMessage = analyzer.isAlarmMessage(text);
    }

}
