package ray.chang.robobob.command;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ray.chang.robobob.service.GameService;
import ray.chang.robobob.util.Util;

import java.util.Scanner;

@ShellComponent
public class StarterCommand {

    public static final String GAME_STARTER_DESCRIPTION = "Start the game";
    public static final String START_MESSAGE = "Type \"start\" to start the game or \"quit\" to leave...";
    private static final String WELCOME_MESSAGE = "Welcome to Robobob...";
    private static final String DESCRIPTION_MESSAGE = "I can help you solve arithmetic problems or answer basic questions like What’s your name?";
    private static final String QUESTION_PROMPT = "Question>";
    private static final String ANSWER_PROMPT = "Answer: ";
    public static final String THANK_YOU_MESSAGE = "Thank you for playing. See you next time!";

    @Autowired
    GameService gameService;

    @PostConstruct
    public void init() {
        Util.printBanner();
        System.out.println(START_MESSAGE);
    }

    @ShellMethod(GAME_STARTER_DESCRIPTION)
    public void start() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(DESCRIPTION_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(QUESTION_PROMPT);
            String question = scanner.nextLine();
            question = question.trim();

            if (question.equals("quit")) break;

            try {
                String answer = gameService.answerQuestion(question);
                System.out.println(ANSWER_PROMPT + answer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(THANK_YOU_MESSAGE);
    }
}
