package ray.chang.robobob.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ray.chang.robobob.answerprovider.AnswerProviderFactory;
import ray.chang.robobob.answerprovider.impl.ArithmeticAnswerProvider;
import ray.chang.robobob.answerprovider.impl.PredefinedAnswerProvider;

import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AnswerProviderFactoryTest {

    @Autowired
    AnswerProviderFactory questionFactory;

    @Test
    public void test() throws InvalidPropertiesFormatException {
        assertEquals(questionFactory.find("2+5").getClass(), ArithmeticAnswerProvider.class);
        assertEquals(questionFactory.find(" 100 ").getClass(), ArithmeticAnswerProvider.class);
        assertEquals(questionFactory.find(" 3 ^ 5 ").getClass(), ArithmeticAnswerProvider.class);

        assertEquals(questionFactory.find("hello").getClass(), PredefinedAnswerProvider.class);
        assertEquals(questionFactory.find("").getClass(), PredefinedAnswerProvider.class);
        assertEquals(questionFactory.find("  ").getClass(), PredefinedAnswerProvider.class);
    }
}
