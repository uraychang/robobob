package ray.chang.robobob.answerprovider;

public interface AnswerProvidable {
    String answer(String question);
    boolean belongsToThis(String question);
    default int getPriority() {
        return Integer.MAX_VALUE;
    }
}
