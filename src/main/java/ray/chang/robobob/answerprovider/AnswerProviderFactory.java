package ray.chang.robobob.answerprovider;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@Component
public class AnswerProviderFactory {
    public static final String FIND_ANSWERPROVIDER_FAILED_MESSAGE = "Failed to find a suitable AnswerProvider";

    @Autowired
    private ApplicationContext applicationContext;

    private List<AnswerProvidable> answerProviders;

    @PostConstruct
    public void init() {
        answerProviders = new ArrayList<>(applicationContext.getBeansOfType(AnswerProvidable.class).values());
        answerProviders.sort(Comparator.comparingInt(AnswerProvidable::getPriority));
    }

    public AnswerProvidable find(String question) throws InvalidPropertiesFormatException {
        for (AnswerProvidable answerProvider : answerProviders) {
            if (answerProvider.belongsToThis(question)) {
                return answerProvider;
            }
        }
        throw new InvalidPropertiesFormatException(FIND_ANSWERPROVIDER_FAILED_MESSAGE);
    }
}
