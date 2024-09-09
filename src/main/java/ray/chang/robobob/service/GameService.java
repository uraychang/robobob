package ray.chang.robobob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.chang.robobob.answerprovider.AnswerProvidable;
import ray.chang.robobob.answerprovider.AnswerProviderFactory;

@Service
public class GameService {

    public static final String UNABLE_ANSWER_MESSAGE = "Unable to answer the question. Please try to ask another question";
    @Autowired
    AnswerProviderFactory answerProviderFactory;

    public String answerQuestion(String question) {
        try {
            AnswerProvidable answerProvider = answerProviderFactory.find(question);
            return answerProvider.answer(question);
        } catch (Exception e) {
            return UNABLE_ANSWER_MESSAGE;
        }
    }
}
