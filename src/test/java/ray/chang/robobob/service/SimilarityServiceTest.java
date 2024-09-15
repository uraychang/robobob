package ray.chang.robobob.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ray.chang.robobob.answerprovider.impl.PredefinedAnswerProvider;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SimilarityServiceTest {

    @Autowired
    SimilarityService similarityService;

    @Test
    public void testCalculateSimilarity() throws InvalidPropertiesFormatException {
        final String askingName = "your name?";
        System.out.println("testing question: " + askingName);

        List<String> similarSentences = List.of(
                "your name?",
                "what's your name",
                "may I know your name",
                "give me the name"
        );

        for (String similarSentence : similarSentences) {
            double sim = similarityService.calculateSimilarity(askingName, similarSentence);
            System.out.println(similarSentence + ", similarity: " + sim);
            assertTrue(sim > PredefinedAnswerProvider.MIN_SIMILARITY_FOR_PREDEFINED_QUESTION);
        }

        List<String> dissimilarSentences = List.of(
                "hello",
                "do you like watching movie",
                "have a nice day",
                "Can you do the homework for me"
        );

        for (String dissimilarSentence : dissimilarSentences) {
            double sim = similarityService.calculateSimilarity(askingName, dissimilarSentence);
            System.out.println(dissimilarSentence + ", similarity: " + sim);
            assertTrue(sim < PredefinedAnswerProvider.MIN_SIMILARITY_FOR_PREDEFINED_QUESTION);
        }
    }
}
