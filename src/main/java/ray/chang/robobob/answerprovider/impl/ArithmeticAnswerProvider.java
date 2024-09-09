package ray.chang.robobob.answerprovider.impl;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;
import ray.chang.robobob.answerprovider.AnswerProvidable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ArithmeticAnswerProvider implements AnswerProvidable {

    private static final Pattern PATTERN = Pattern.compile("^\\s*[-+]?(\\d+(\\.\\d+)?)(\\s*[-+*/]\\s*[-+]?(\\d+(\\.\\d+)?))*\\s*$");
    public static final String FAILED_TO_EVALUATE_THE_ARITHMETIC_EXPRESSION_MESSAGE = "Failed to evaluate the arithmetic expression. Make it's in the correct format";

    @Override
    public String answer(String question) {
        try {
            Expression expression = new ExpressionBuilder(question).build();
            double result = expression.evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            return FAILED_TO_EVALUATE_THE_ARITHMETIC_EXPRESSION_MESSAGE;
        }
    }

    @Override
    public boolean belongsToThis(String question) {
        Matcher matcher = PATTERN.matcher(question);
        return matcher.find();
    }

    @Override
    public int getPriority() {
        return Integer.MIN_VALUE;
    }
}
