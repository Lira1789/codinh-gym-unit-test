package codinggym.analyzers;

import com.codinggym.analyzers.MeetingTimeAnalyzer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MeetingTimeAnalyzerTest {

    private final LocalDate date;
    private final int meetingMinutes;
    private final boolean expectedResult;
    private MeetingTimeAnalyzer analyzer;

    public MeetingTimeAnalyzerTest(LocalDate date, int meetingMinutes, boolean expectedResult) {
        this.date = date;
        this.meetingMinutes = meetingMinutes;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
                {LocalDate.of(2021, 8, 2), 35, false},
                {LocalDate.of(2021, 8, 3), 125, true},
                {LocalDate.of(2021, 8, 4), 68, false},
                {LocalDate.of(2021, 8, 5), 155, true},
                {LocalDate.of(2021, 8, 6), 45, false},
                {LocalDate.of(2021, 8, 7), 0, true}
        });
    }

    @Before
    public void setUp() throws Exception {
        analyzer = MeetingTimeAnalyzer.getInstance();
    }

    @Test
    public void isEnoughMeetingTime() {
        System.out.printf("Parameters %d - %s \n", meetingMinutes, date.toString());
        boolean actualResult = analyzer.isEnoughMeetingTime(date, meetingMinutes);
        assertEquals(expectedResult, actualResult);
    }
}