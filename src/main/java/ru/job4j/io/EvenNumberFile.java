package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
           }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int dig = Integer.parseInt(line);
                String answer = (dig % 2 == 0) ? dig + " - " + "четное" : dig + " - " + "нечетное";
                System.out.println(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}