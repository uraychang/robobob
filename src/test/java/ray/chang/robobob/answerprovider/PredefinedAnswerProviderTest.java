package ray.chang.robobob.answerprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ray.chang.robobob.answerprovider.impl.PredefinedAnswerProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ray.chang.robobob.answerprovider.impl.PredefinedAnswerProvider.UNABLE_TO_ANSWER_QUESTION_MESSAGE;

@SpringBootTest
@ActiveProfiles("test")
public class PredefinedAnswerProviderTest {

    @Autowired
    PredefinedAnswerProvider predefinedAnswerProvider;

   @Test
   public void testAnswer() {
       List<String> nameQuestions = List.of(
               "what's your name",
               "your name?",
               "please give the name",
               "name is?"
       );

       for (String nameQuestion : nameQuestions) {
           assertEquals("My name is Robobob.", predefinedAnswerProvider.answer(nameQuestion));
       }

       List<String> ageQuestions = List.of(
               "what's your age",
               "Please provide your age.",
               "Your age?"
       );

       for (String ageQuestion : ageQuestions) {
           assertEquals("I'm just few days old.", predefinedAnswerProvider.answer(ageQuestion));
       }

       List<String> invalidQuestions = List.of(
               "what type of movie do you like",
               "hello",
               "do you live in London?"
       );

       for (String invalidQuestion : invalidQuestions) {
           assertTrue(predefinedAnswerProvider.answer(invalidQuestion).contains(UNABLE_TO_ANSWER_QUESTION_MESSAGE));
       }
   }
}
