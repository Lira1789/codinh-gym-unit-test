package codinggym.analyzers;

import com.codinggym.analyzers.MessageAnalyzer;
import com.codinggym.exceptions.ShortMessageException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MessageAnalyzerJunit5Test {

    private MessageAnalyzer messageAnalyzer;

    @BeforeEach
    public void setUp() {
        messageAnalyzer = MessageAnalyzer.getInstance();
    }

    @Test
    public void countWords_whenMessageIsNull_thenReturnsZero() {
        //given

        //when
        int actualResult = messageAnalyzer.countWords(null);

        //then
        Assertions.assertEquals(0, actualResult, "Message");
    }

    @Test
    public void countWords_whenMessageIsEmpty_thenReturnsZero() {
        //given
        String message = "";

        //when
        int actualResult = messageAnalyzer.countWords(message);

        //then
        Assertions.assertEquals(0, actualResult, "Message");
    }

    @Test
    @Disabled
    public void countWords_whenMessage_thenWordsCount() {
        //given
        String message = "I love java";
        int expectedResult = 3;

        //when
        int actualResult = messageAnalyzer.countWords(message);

        //then
        Assertions.assertEquals(expectedResult, actualResult, "Message");
    }

    @Test
    public void isWorkRelatedMessage_whenMessageIShort_thenThrowsException() {
        //given
        String message = "yes";

        //when
        Assertions.assertThrows(ShortMessageException.class, () -> messageAnalyzer.isWorkRelatedMessage(message));
    }

    @Test
    public void analyze_whenWorks_thenRunLessThan() {
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> messageAnalyzer.analyze());
    }


}