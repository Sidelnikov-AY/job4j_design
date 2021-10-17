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
    private String inFile;
    private String outFile;
    private String delimiter;
    private List<String> filterArray;


    public CSVReader() { }

    private void init(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Argument mismatch");
        }

        inFile = argsName.get("path");
        Path checkFile = Path.of(inFile);
        if (!Files.exists(checkFile) || Files.isDirectory(checkFile)) {
            throw new IllegalArgumentException("wrong input file path");
        }

        outFile = argsName.get("out");
        if (!outFile.equals("stdout")) {
            Path checkF = Path.of(outFile);
            if (checkF == null || Files.isDirectory(checkF)) {
                throw new IllegalArgumentException("wrong output file path");
            }
        }

        delimiter = argsName.get("delimiter");
        if (delimiter == null) {
            throw new IllegalArgumentException("wrong output file path");
        }

        String filter = argsName.get("filter");
        if (filter == null) {
            throw new IllegalArgumentException("wrong output file path");
        }
        filterArray = List.of(filter.split(","));

    }

    private List<String> getFirstLine(String inFile, String delimiter) throws IOException {
        List<String> firstLine = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            Scanner sc = new Scanner(br.readLine()).useDelimiter(delimiter);
            while (sc.hasNext()) {
                firstLine.add(sc.next());
            }
        } catch (Exception e) {
                e.printStackTrace();
            }
            return firstLine;
    }

    private void readSource(String inFile, String outFile, List<String> filterArray, String delimiter)
            throws IOException {
        List<String> temp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            Scanner sc = new Scanner(br.readLine()).useDelimiter(delimiter);
            String line = br.readLine();
             while (line != null) {
                sc = new Scanner(line).useDelimiter(delimiter);
                for (int i = 0; i < getFirstLine(inFile, delimiter).size(); i++) {
                    temp.add(sc.next());
                }
                 writeResult(temp, inFile, outFile, filterArray, delimiter);
                 temp.clear();
                 line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeResult(List<String> temp, String inFile, String outFile, List<String> filterArray,
                             String delimiter) throws IOException {
            String out = "";
            for (int i = 0; i < getFirstLine(inFile, delimiter).size(); i++) {
                if (filterArray.contains(getFirstLine(inFile, delimiter).get(i))) {
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
        CSVReader reader = new CSVReader();
        reader.init(args);
        reader.readSource(reader.inFile, reader.outFile, reader.filterArray, reader.delimiter);
     }
}
