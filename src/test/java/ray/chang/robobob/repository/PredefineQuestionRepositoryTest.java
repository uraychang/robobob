package ray.chang.robobob.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ray.chang.robobob.model.PredefinedQuestion;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PredefineQuestionRepositoryTest {

    @Autowired
    PredefinedQuestionRepository repository;

    @Test
    public void test() throws IOException {
        List<PredefinedQuestion> predefinedQuestions = repository.findAll();
        predefinedQuestions.sort(Comparator.comparing(PredefinedQuestion::getQuestion));

        PredefinedQuestion question1 = new PredefinedQuestion();
        question1.setQuestion("What is your age?");
        question1.setAnswer("I'm just few days old.");
        PredefinedQuestion question2 = new PredefinedQuestion();
        question2.setQuestion("What is your name?");
        question2.setAnswer("My name is Robobob.");

        assertEquals(2, predefinedQuestions.size());
        assertEquals(question1, predefinedQuestions.get(0));
        assertEquals(question2, predefinedQuestions.get(1));
    }
}
