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
    private final String inFile;
    private String outFile;
    private String delimiter;
    private List<String> filterArray;


    public CSVReader(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Argument mismatch");
        }
        this.inFile = readArgsIn(argsName);
        this.outFile = readArgsOut(argsName);
        this.delimiter = readArgsDelimiter(argsName);
        this.filterArray = readArgsFilterArray(argsName);
    }

    public String getInFile() {
        return inFile;
    }

    public String getOutFile() {
        return outFile;
    }

    public String getDelimiter() {
        return delimiter;
    }

    private String readArgsIn(ArgsName args) {
        String inFile;
        inFile = args.get("path");
        Path checkFile = Path.of(inFile);
        if (!Files.exists(checkFile) || Files.isDirectory(checkFile)) {
            System.out.println("wrong input file path");
        }
        return inFile;
    }

    private String readArgsOut(ArgsName args) {
        outFile = args.get("out");
        if (!outFile.equals("stdout")) {
            Path checkFile = Path.of(outFile);
            if (!Files.exists(checkFile) || Files.isDirectory(checkFile)) {
                System.out.println("wrong output file path");
            }
        }
        return outFile;
    }

    private String readArgsDelimiter(ArgsName args) {
        delimiter = args.get("delimiter");
        if (delimiter == null) {
            System.out.println("Wrong delimiter");
        }
        return delimiter;
    }

    private List<String> readArgsFilterArray(ArgsName args) {
        String filter = args.get("filter");
        if (filter != null) {
            filterArray = List.of(filter.split(","));
        } else {
            System.out.println("no filter set");
            filterArray = null;
        }
        return filterArray;
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

    private void writeResult(List<String> temp, String inFile, String outFile, List<String> filterArray, String delimiter) throws IOException {
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
        CSVReader reader = new CSVReader(args);
        reader.readSource(reader.getInFile(), reader.getOutFile(), reader.filterArray, reader.getDelimiter());
        // -path=./src/main/java/ru/job4j/io/in.csv -delimiter=";" -out=./src/main/java/ru/job4j/io/out.csv -filter=name,age
    }
}
