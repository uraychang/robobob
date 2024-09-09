package ray.chang.robobob.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.InvalidPropertiesFormatException;

@Data
public class PredefinedQuestion {
    public static final String THE_QUESTION_FIELD_CAN_NOT_BE_BLANK_MESSAGE = "The question field can not be blank";
    public static final String THE_ANSWER_FIELD_CAN_NOT_BE_BLANK_MESSAGE = "The answer field can not be blank";

    String question;
    String answer;

    public static void validate(PredefinedQuestion predefinedQuestion) throws InvalidPropertiesFormatException {
        if (StringUtils.isBlank(predefinedQuestion.getQuestion())) {
            throw new InvalidPropertiesFormatException(THE_QUESTION_FIELD_CAN_NOT_BE_BLANK_MESSAGE);
        }
        if (StringUtils.isBlank(predefinedQuestion.getAnswer())) {
            throw new InvalidPropertiesFormatException(THE_ANSWER_FIELD_CAN_NOT_BE_BLANK_MESSAGE);
        }
    }
}
