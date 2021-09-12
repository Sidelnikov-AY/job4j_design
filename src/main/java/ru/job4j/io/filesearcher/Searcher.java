package ru.job4j.io.filesearcher;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class Searcher {
    String[] args;
    private String directory;
    private String fileToSearch;
    private String searchType;
    private String outputFile;

    public Searcher(String[] args) {
        this.args = args;
    }

    private void init(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Incorrect number of arguments. "
                    + "Right format: java -jar searcher.jar -d=directory -n=fileToSearch -t=searchType -o=outputFile");
        }

        directory = argsName.get("d");
        Path checkFile = Path.of(directory);
        if (!Files.exists(checkFile) || !Files.isDirectory(checkFile)) {
            throw new IllegalArgumentException("Incorrect directory to search: " + checkFile.toAbsolutePath());
        }

        fileToSearch = argsName.get("n");
        if (fileToSearch == null) {
            throw new IllegalArgumentException("No data to search file");
            }

        searchType = argsName.get("t");
        if (searchType == null) {
            throw new IllegalArgumentException("No search type specified. ");
        }

        outputFile = argsName.get("o");
        if (outputFile == null || Files.isDirectory(Path.of(outputFile))) {
            throw new IllegalArgumentException("Wrong output file path");
        }
    }

    private void searchByType() throws IOException {
        Path start = Paths.get(directory);
        Predicate<Path> condition = null;
        ArrayList<Path> paths = null;
        Pattern pattern;
        switch (searchType) {
            case "name":
                condition = p -> p.toFile().getName().equals(fileToSearch);
                break;
            case "regex":
                pattern = Pattern.compile(fileToSearch);
                condition = p -> pattern.matcher(p.toFile().getName()).find();
                break;
            case "mask":
                String temp = fileToSearch.replaceAll("\\*", "(\\\\S\\*)");
                temp = temp.replaceAll("\\?", "(\\\\S\\?)");
                pattern = Pattern.compile(temp);
                condition = p -> p.toFile().getName().matches(pattern.toString());
                System.out.println(pattern);
                break;
            default:
                System.out.println("Something went wrong");
        }
        paths = search(start, condition);
        save(outputFile, paths);
        System.out.println("Search completed");

    }

    private ArrayList<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private void save(String outputFile, List<Path> paths) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile, Charset.forName("UTF-8"), true))) {
            for (Path path: paths) {
              pw.println(path.toString());
            }
            System.out.println("Files found: " + paths.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        Searcher searcher = new Searcher(args);
        searcher.init(args);
        searcher.searchByType();

        //-d=./src/main/java/ru/job4j/io/filesearcher -n=*.java -t=mask -o=./src/main/java/ru/job4j/io/filesearcher/out.csv

    }
}
