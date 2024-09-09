package ray.chang.robobob.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ray.chang.robobob.model.PredefinedQuestion;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<PredefinedQuestion> readPredefinedQuestion(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource(fileName);
        InputStream inputStream = resource.getInputStream();
        List<PredefinedQuestion> result = objectMapper.readValue(inputStream, new TypeReference<List<PredefinedQuestion>>() {});
        for (PredefinedQuestion predefinedQuestion : result) {
            PredefinedQuestion.validate(predefinedQuestion);
        }
        return result;
    }
}
