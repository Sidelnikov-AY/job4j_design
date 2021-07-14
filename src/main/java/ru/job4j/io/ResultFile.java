package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for(int i = 1; i <= 9; i++) {
                for (int j =1; j <= 9; j++){
                    out.write((i + " * " + j  + " = " + i * j + "\n").getBytes());
                    }
                out.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}