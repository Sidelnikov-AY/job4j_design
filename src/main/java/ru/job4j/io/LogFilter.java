package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            list = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> out = new ArrayList<>();
        int i = 0;
        for (String line: list) {
            String[] subStr = line.split(" ");
            int temp =Integer.parseInt(subStr[subStr.length - 2] );
            if(temp == 404) {
                    out.add(i, line);
                    i++;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}