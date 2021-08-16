package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private List<List<String>> input = new ArrayList<>();
    private String inFile;
    private String outFile;
    private String delimiter;
    List<String> filterArray = new ArrayList<>();


    public CSVReader(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Argument mismatch");
        }
        readArgs(argsName);
    }

    private void readArgs(ArgsName args) {

        inFile = args.get("path");
        Path checkFile = Path.of(inFile);
        if (!Files.exists(checkFile) || Files.isDirectory(checkFile)) {
            System.out.println("wrong input file path");
        }
        outFile = args.get("out");
        if (!outFile.equals("stdout")) {
        Path checkFile2 = Path.of(outFile);
            if (!Files.exists(checkFile) || Files.isDirectory(checkFile2)) {
                System.out.println("wrong output file path");
            }
        }
        delimiter = args.get("delimiter");
        if (delimiter == null) {
            System.out.println("Wrong delimiter");
        }

        String filter = args.get("filter");
        if (filter != null) {
            filterArray = List.of(filter.split(","));
        } else {
            System.out.println("no filter set");
            filterArray = null;
        }
    }

    private void readSource() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            String line;
             while ((line = br.readLine()) != null) {
                List<String> lineToArray = new ArrayList<>();
                Scanner sc = new Scanner(new ByteArrayInputStream(line.getBytes())).useDelimiter(delimiter);
                while (sc.hasNext()) {
                    lineToArray.add(sc.next());
                }
                input.add(lineToArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeResult() {
        List<Integer> index = new ArrayList<>();
        List<String> head = input.get(0);
        for (String el : head) {
            if (filterArray.contains(el)) {
                index.add(1);
            } else {
                index.add(0);
            }
        }
        List<String> temp = new ArrayList<>();
        for (List<String> line : input) {
            String out = "";
            for (int i = 0; i < line.size(); i++) {
                if (index.get(i) == 1) {
                    out += String.format("%s,", line.get(i));
                }
            }
            temp.add(out);
        }

        if (outFile.equals("stdout")) {
            temp.forEach(System.out::println);
        } else {
            saveFile(temp, outFile);
        }
    }


    private static void saveFile(List<String> in, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            in.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(args);
        reader.readSource();
        reader.writeResult();
    }
}
