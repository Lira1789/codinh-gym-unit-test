package com.codinggym.models;

import com.codinggym.analyzers.EmployeeAnalyzer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    private String name;
    private String surname;
    private String position;
    private List<Message> messages;
    private boolean isReliable;

    public Employee(String name, String surname, String position, List<Message> messages) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.messages = messages;
        this.isReliable = EmployeeAnalyzer.getInstance().isReliableEmployee(messages);
    }

}
