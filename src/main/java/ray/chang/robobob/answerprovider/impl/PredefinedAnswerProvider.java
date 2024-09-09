package ray.chang.robobob.answerprovider.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ray.chang.robobob.answerprovider.AnswerProvidable;
import ray.chang.robobob.model.PredefinedQuestion;
import ray.chang.robobob.repository.PredefinedQuestionRepository;
import ray.chang.robobob.service.SimilarityService;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Random;

@Component
public class PredefinedAnswerProvider implements AnswerProvidable {

    public static double MIN_SIMILARITY_FOR_PREDEFINED_QUESTION = 0.83;
    public static final String UNABLE_TO_ANSWER_QUESTION_MESSAGE = "Sorry, I'm unable to answer the question.";
    public static final String FAILED_TO_FIND_ALL_PREDEFINED_QUESTIONS_MESSAGE = "Failed to find all predefined questions";
    @Autowired
    PredefinedQuestionRepository predefinedQuestionRepository;

    @Autowired
    SimilarityService similarityService;

    private List<PredefinedQuestion> predefinedQuestions;

    @PostConstruct
    public void init() throws IOException {
        try {
            predefinedQuestions = predefinedQuestionRepository.findAll();
        } catch (IOException e) {
            System.err.println(FAILED_TO_FIND_ALL_PREDEFINED_QUESTIONS_MESSAGE);
            throw e;
        }
    }

    @Override
    public String answer(String question) {
        double answerSimilarity = 0.0;
        String answer = "";
        try {
            for (PredefinedQuestion predefinedQuestion : predefinedQuestions) {
                double similarity = similarityService.calculateSimilarity(predefinedQuestion.getQuestion(), question);
                if (similarity > answerSimilarity) {
                    answerSimilarity = similarity;
                    answer = predefinedQuestion.getAnswer();
                }
            }
            return answerSimilarity >= MIN_SIMILARITY_FOR_PREDEFINED_QUESTION ? answer : getUnableToAnswerQuestionMessage();
        } catch (InvalidPropertiesFormatException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    private String getUnableToAnswerQuestionMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(predefinedQuestions.size());
        String exampleQuestion = predefinedQuestions.get(randomIndex).getQuestion();
        return UNABLE_TO_ANSWER_QUESTION_MESSAGE + " You can try to ask me \" " + exampleQuestion + " \"";
    }

    @Override
    public boolean belongsToThis(String question) {
        return true; // currently only 2 types of question are supported, so always return true
    }
}
