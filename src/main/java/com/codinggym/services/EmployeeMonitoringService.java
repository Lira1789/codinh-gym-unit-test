package com.codinggym.services;

import com.codinggym.models.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeMonitoringService {

    private MailService mailService;

    private TimeService timeService;

    public void notifyManager(List<Employee> employees) {

        if (timeService.isTimeForMonitoring(LocalDate.now())) {

            employees.stream()
                    .filter(employee -> !employee.isReliable())
                    .forEach(employee -> mailService.sentEmail(
                            String.format("%s %s is not reliable anymore", employee.getName(), employee.getSurname())));
        }
    }
}
