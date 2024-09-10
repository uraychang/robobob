package ray.chang.robobob.answerprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ray.chang.robobob.answerprovider.impl.ArithmeticAnswerProvider;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ray.chang.robobob.answerprovider.impl.ArithmeticAnswerProvider.FAILED_TO_EVALUATE_THE_ARITHMETIC_EXPRESSION_MESSAGE;

@SpringBootTest
@ActiveProfiles("test")
public class ArithmeticAnswerProviderTest {

    @Autowired
    ArithmeticAnswerProvider arithmeticAnswerProvider;

    @Test
    public void testAnswer() {
        Map<String, String> questionAnswerMap = Map.of(
                "3 + 4", "7.0",
                "10 - 2", "8.0",
                "5 * 6", "30.0",
                "8 / 4", "2.0",
                "5 ^ 2", "25.0",
                "7 *  8", "56.0",
                "1 /2 ", "0.5",
                "2*10.5+1", "22.0",
                "(-3) ^ (1/3)", "NaN"
        );

        for (String question : questionAnswerMap.keySet()) {
            assertEquals(questionAnswerMap.get(question), arithmeticAnswerProvider.answer(question));
        }

        List<String> invalidQuestion = List.of(
                "3 + 3 2",
                "5/0",
                "abc",
                "  ",
                "5 0 0 0"
        );

        for (String question : invalidQuestion) {
            assertEquals(FAILED_TO_EVALUATE_THE_ARITHMETIC_EXPRESSION_MESSAGE, arithmeticAnswerProvider.answer(question));
        }
    }
}
