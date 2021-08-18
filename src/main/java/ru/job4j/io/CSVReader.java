package ru.job4j.io;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private String inFile;
    private static String outFile;
    private String delimiter;
    private static List<String> filterArray = new ArrayList<>();
    private static List<String> firstLine = new ArrayList<>();
    private static List<String> temp = new ArrayList<>();


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
            Scanner sc = new Scanner(br.readLine()).useDelimiter(delimiter);
            while (sc.hasNext()) {
                firstLine.add(sc.next());
            }
            String line = br.readLine();
             while (line != null) {
                sc = new Scanner(line).useDelimiter(delimiter);
                for (int i = 0; i < firstLine.size(); i++) {
                    temp.add(sc.next());
                }
                 writeResult();
                 temp.clear();
                 line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeResult() {
            String out = "";
            for (int i = 0; i < firstLine.size(); i++) {
                if (filterArray.contains(firstLine.get(i))) {
                    out += String.format("%s,", temp.get(i));
                }
        }
        if (outFile.equals("stdout")) {
            System.out.println(out);
        } else {
            saveFile(out, outFile);
        }
    }

    private static void saveFile(String in, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            pw.println(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(args);
        reader.readSource();
        // -path=./src/main/java/ru/job4j/io/in.csv -delimiter=";" -out=./src/main/java/ru/job4j/io/out.csv -filter=name,age
    }
}
