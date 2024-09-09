package ray.chang.robobob.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void test() {
        List<String> invalidFiles = List.of(
                "classpath:predefined-question-empty-question-field.json",
                "classpath:predefined-question-missing-answer-field.json"
        );

        for (String invalidFile : invalidFiles) {
            assertThatThrownBy(() -> fileService.readPredefinedQuestion(invalidFile))
                    .isInstanceOf(InvalidPropertiesFormatException.class);
        }
    }
}
