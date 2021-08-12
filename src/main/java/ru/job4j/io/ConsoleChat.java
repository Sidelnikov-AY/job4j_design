package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static List<String> log = new ArrayList<>();
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("*** Чат. Напишите что-нибудь ***");
        String s = "";
        boolean step = true;
        boolean checkStopContinue = true;
        List<String> answers = readPhrases(botAnswers);
        Scanner scanner = new Scanner(System.in);
        while (!s.equals(OUT)) {
            if (step) {
                s = scanner.nextLine();
                step = !step;
                if (s.equals(STOP)) {
                    checkStopContinue = false;
                }
                if (!checkStopContinue) {
                    step = !step;
                }
                if (s.equals(CONTINUE)) {
                    checkStopContinue = true;
                }
            } else {
                s = generateAnswer(answers);
                System.out.println(s);
                step = !step;
            }
            log.add(s);
        }
        saveLog(path);
    }

    private static List<String> readPhrases(String path) {
        List<String> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            out = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    private static String generateAnswer(List<String> answersArray) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(answersArray.size());
            return answersArray.get(randomIndex);
    }

    private static void saveLog(String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/java/ru/job4j/io/log.txt", "./src/main/java/ru/job4j/io/answers.txt");
        cc.run();
    }
}