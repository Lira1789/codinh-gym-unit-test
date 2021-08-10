package codinggym.analyzers;

import com.codinggym.analyzers.MeetingTimeAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;


public class MeetingTimeAnalyzerJunit5Test {

    private MeetingTimeAnalyzer analyzer;

    static Stream<Arguments> paramsProvider() {
        return Stream.of(
                arguments(false, LocalDate.of(2021, 8, 2), 35),
                arguments(true, LocalDate.of(2021, 8, 3), 125),
                arguments(false, LocalDate.of(2021, 8, 4), 68),
                arguments(true, LocalDate.of(2021, 8, 5), 155),
                arguments(false, LocalDate.of(2021, 8, 6), 45),
                arguments(true, LocalDate.of(2021, 8, 7), 0)
        );
    }

    @BeforeEach
    public void setUp() throws Exception {
        analyzer = MeetingTimeAnalyzer.getInstance();
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    public void isEnoughMeetingTime(boolean expectedResult, LocalDate date, int meetingMinutes) {
        System.out.printf("Parameters %d - %s \n", meetingMinutes, date.toString());
        boolean actualResult = analyzer.isEnoughMeetingTime(date, meetingMinutes);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}