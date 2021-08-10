package codinggym.services;

import com.codinggym.exceptions.WeekendException;
import com.codinggym.models.Employee;
import com.codinggym.models.Message;
import com.codinggym.services.EmployeeMonitoringService;
import com.codinggym.services.MailService;
import com.codinggym.services.TimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeMonitoringServiceJUnitTest {

    @InjectMocks
    private EmployeeMonitoringService service;

    @Mock
    private TimeService timeService;

    @Mock
    private MailService mailService;
    private List<Employee> lessReliableEmployees;
    private List<Employee> moreReliableEmployees;

    @BeforeEach
    public void setUp() throws Exception {

        lessReliableEmployees = List.of(
                new Employee("Jared", "Dunn", "business advisor", getMessagesForReliableEmployee()),
                new Employee("Bertram ", "Gilfoyle", "network engineer", getMessagesForNotReliableEmployee()),
                new Employee("Dinesh ", "Chugtai", "Java developer", getMessagesForNotReliableEmployee()));

        moreReliableEmployees = List.of(
                new Employee("Jared", "Dunn", "business advisor", getMessagesForReliableEmployee()),
                new Employee("Nelson", "Bighetti", "developer", getMessagesForReliableEmployee()),
                new Employee("Dinesh", "Chugtai", "Java developer", getMessagesForNotReliableEmployee()));
    }

    @Test
    public void test() {
        System.out.println(timeService.isTimeForMonitoring(LocalDate.of(2021, 8, 6)));
    }

    @Test
    public void notifyManager_whenItIsWeekend_thenSentNoEmail() {
        //given
        when(timeService.isTimeForMonitoring(any())).thenThrow(WeekendException.class);

        //when
        Assertions.assertThrows(WeekendException.class, () -> service.notifyManager(lessReliableEmployees));

        //then
        verify(mailService, never()).sentEmail(anyString());
    }

    @Test
    public void notifyManager_whenItNotMonitoringTime_thenSentNoEmail() {
        //given
        when(timeService.isTimeForMonitoring(any())).thenReturn(false);

        //when
        service.notifyManager(lessReliableEmployees);

        //then
        verify(mailService, never()).sentEmail(anyString());
    }


    @Test
    public void notifyManager_whenItIsMonitoringTimeAndTwoNotReliableEmployee_thenSentEmailTwice() {
        //given
        when(timeService.isTimeForMonitoring(any())).thenReturn(true);

        //when
        service.notifyManager(lessReliableEmployees);

        //then
        verify(mailService, times(2)).sentEmail(anyString());
    }

    @Test
    public void givenMultiAssertion() {
        assertAll(
                "Heading",
                () -> assertEquals(5, 2 + 3, "Message"),
                () -> Assertions.assertEquals("string1", "string1", "Message")
        );
    }


    private List<Message> getMessagesForReliableEmployee() {
        List<Message> messages = new ArrayList<>();
        IntStream.range(0, 7).forEach(index -> messages.add(getWorkRelatedMessage()));
        IntStream.range(0, 2).forEach(index -> messages.add(getNeutralMessage()));
        messages.add(getAlarmMessage());
        return messages;

    }

    private List<Message> getMessagesForNotReliableEmployee() {
        List<Message> messages = new ArrayList<>();
        IntStream.range(0, 4).forEach(index -> messages.add(getWorkRelatedMessage()));
        IntStream.range(0, 5).forEach(index -> messages.add(getAlarmMessage()));
        messages.add(getNeutralMessage());
        return messages;

    }

    public Message getWorkRelatedMessage() {
        List<String> workRelatedMessages = List.of(
                "I approved his PR",
                "I sent a letter and a message in slack",
                "I sent him a message in slack about Jenkins",
                "A created a story to track our release activities",
                "I merged my PR and moved this story to the test in the Jira");

        return new Message(workRelatedMessages.get(new Random().nextInt(5)));
    }

    public Message getAlarmMessage() {
        List<String> alarmMessages = List.of(
                "I can't find him in LinkedIn",
                "I've updated my VC",
                "My salary is 10 000",
                "I've an invitation to the interview",
                "I am not satisfied with my salary");

        return new Message(alarmMessages.get(new Random().nextInt(5)));
    }

    public Message getNeutralMessage() {
        List<String> alarmMessages = List.of(
                "He is a very good developer",
                "Love this company",
                "I had to stay late yesterday",
                "This new framework is awesome",
                "Let's get some coffee");

        return new Message(alarmMessages.get(new Random().nextInt(5)));
    }
}