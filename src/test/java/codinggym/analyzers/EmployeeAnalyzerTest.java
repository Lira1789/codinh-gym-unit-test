package codinggym.analyzers;

import com.codinggym.analyzers.EmployeeAnalyzer;
import com.codinggym.models.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmployeeAnalyzerTest {

    private EmployeeAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = EmployeeAnalyzer.getInstance();
    }

    @Test
    public void isReliableEmployee_whenEmployeeIsNotReliable_thenReturnsFalse() {
        //given
        List<Message> messages = getMessagesForNotReliableEmployee();

        //when
        boolean actualResult = analyzer.isReliableEmployee(messages);

        //then
        assertFalse(actualResult);
    }

    @Test
    public void isReliableEmployee_whenEmployeeIsReliable_thenReturnsTrue() {
        //given
        List<Message> messages = getMessagesForReliableEmployee();

        //when
        boolean actualResult = analyzer.isReliableEmployee(messages);

        //then
        assertTrue(actualResult);
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

    private Message getWorkRelatedMessage() {
        List<String> workRelatedMessages = List.of(
                "I approved his PR",
                "I sent a letter and a message in slack",
                "I sent him a message in slack about Jenkins",
                "A created a story to track our release activities",
                "I merged my PR and moved this story to the test in the Jira");

        return new Message(workRelatedMessages.get(new Random().nextInt(5)));
    }

    private Message getAlarmMessage() {
        List<String> alarmMessages = List.of(
                "I can't find him in LinkedIn",
                "I've updated my VC",
                "My salary is 10 000",
                "I've an invitation to the interview",
                "I am not satisfied with my salary");

        return new Message(alarmMessages.get(new Random().nextInt(5)));
    }

    private Message getNeutralMessage() {
        List<String> alarmMessages = List.of(
                "He is a very good developer",
                "Love this company",
                "I had to stay late yesterday",
                "This new framework is awesome",
                "Let's get some coffee");

        return new Message(alarmMessages.get(new Random().nextInt(5)));
    }
}