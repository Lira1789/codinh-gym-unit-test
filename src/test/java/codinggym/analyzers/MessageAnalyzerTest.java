package codinggym.analyzers;

import com.codinggym.analyzers.MessageAnalyzer;
import com.codinggym.exceptions.ShortMessageException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageAnalyzerTest {

    private MessageAnalyzer messageAnalyzer;

    @Before
    public void setUp() {
        messageAnalyzer = MessageAnalyzer.getInstance();
    }

    @Test
    public void countWords_whenMessageIsNull_thenReturnsZero() {
        //given

        //when
        int actualResult = messageAnalyzer.countWords(null);

        //then
        assertEquals("Message", 0, actualResult);
    }

    @Test
    public void countWords_whenMessageIsEmpty_thenReturnsZero() {
        //given
        String message = "";

        //when
        int actualResult = messageAnalyzer.countWords(message);

        //then
        assertEquals("Message", 0, actualResult);
    }

    @Test
    @Ignore
    public void countWords_whenMessage_thenWordsCount() {
        //given
        String message = "I love java";
        int expectedResult = 3;

        //when
        int actualResult = messageAnalyzer.countWords(message);

        //then
        assertEquals("Message", expectedResult, actualResult);
    }

    @Test(expected = ShortMessageException.class)
    public void isWorkRelatedMessage_whenMessageIShort_thenThrowsException() {
        //given
        String message = "yes";

        //when
        messageAnalyzer.isWorkRelatedMessage(message);
    }

    @Test(timeout = 1000)
    public void analyze_whenWorks_thenRunLessThan() {
        messageAnalyzer.analyze();
    }


}