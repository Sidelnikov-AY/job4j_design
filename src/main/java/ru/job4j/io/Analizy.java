package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public Analizy() {

    };
    public void unavailable(String source, String target) {
        List<String> time = new ArrayList<>();
        String start = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String line = read.readLine();
            while (line != null) {
                String[] listFromLine = line.split(" ");
                int status = Integer.parseInt(listFromLine[0]);
                if (status >= 400 && start == null) {
                    start = listFromLine[1];
                }
                if (status < 400 && start != null) {
                    time.add(start + ";" + listFromLine[1] + ";");
                    start = null;

                }
                line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (time.size() == 0) {
            return;
        }
        save(time, target);

    }

    public static void save(List<String> list, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String e: list) {
                out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analysis = new Analizy();
        analysis.unavailable("analysis.csv", "out.csv");
    }
}