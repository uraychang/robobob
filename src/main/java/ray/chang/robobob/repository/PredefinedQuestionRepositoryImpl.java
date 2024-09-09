package ray.chang.robobob.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.chang.robobob.model.PredefinedQuestion;
import ray.chang.robobob.service.FileService;

import java.io.IOException;
import java.util.List;

@Repository
public class PredefinedQuestionRepositoryImpl implements PredefinedQuestionRepository {

    public static final String PREDEFINED_QUESTION_JSON_FILE_PATH = "classpath:predefined-question.json";

    @Autowired
    private FileService fileService;

    @Override
    public List<PredefinedQuestion> findAll() throws IOException {
        return fileService.readPredefinedQuestion(PREDEFINED_QUESTION_JSON_FILE_PATH);
    }

}
