package ray.chang.robobob.service;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

@Service
public class SimilarityService {

    public static final String SENTENCE_SIMILARITY_PY_FILE_PATH = "python/sentence_similarity.py";
    public static final String PYTHON_3 = "python3";
    public static final String FAILED_TO_CALCULATE_SIMILARITY_MESSAGE = "Failed to calculate similarity between 2 sentences";

    public Double calculateSimilarity(String sentence1, String sentence2) throws InvalidPropertiesFormatException {
        return Double.valueOf(runCalculateSimilarityPython(sentence1, sentence2));
    }

    private String runCalculateSimilarityPython(String sentence1, String sentence2) throws InvalidPropertiesFormatException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

        try {
            CommandLine commandLine = new CommandLine(PYTHON_3);
            commandLine.addArgument(SENTENCE_SIMILARITY_PY_FILE_PATH);
            commandLine.addArguments(new String[]{sentence1, sentence2});

            DefaultExecutor executor = new DefaultExecutor();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            executor.setStreamHandler(streamHandler);

            int exitCode = executor.execute(commandLine);

            if (exitCode == 0) {
                return outputStream.toString();
            } else {
                System.err.println(FAILED_TO_CALCULATE_SIMILARITY_MESSAGE);
                throw new InvalidPropertiesFormatException(FAILED_TO_CALCULATE_SIMILARITY_MESSAGE);
            }

        } catch (IOException e) {
            throw new InvalidPropertiesFormatException(FAILED_TO_CALCULATE_SIMILARITY_MESSAGE);
        }
    }
}
