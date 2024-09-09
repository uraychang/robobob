package ray.chang.robobob.repository;

import ray.chang.robobob.model.PredefinedQuestion;

import java.io.IOException;
import java.util.List;

public interface PredefinedQuestionRepository {
    List<PredefinedQuestion> findAll() throws IOException;
}
